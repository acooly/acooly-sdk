/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-09 18:05
 */
package cn.acooly.sdk.aliyun.express.service.impl;

import cn.acooly.sdk.aliyun.common.transport.AliyunRequest;
import cn.acooly.sdk.aliyun.common.transport.AliyunResponse;
import cn.acooly.sdk.aliyun.express.domain.ExpressCompany;
import cn.acooly.sdk.aliyun.express.domain.ExpressInfo;
import cn.acooly.sdk.aliyun.express.domain.ExpressTrack;
import cn.acooly.sdk.aliyun.express.dto.tianyan.*;
import cn.acooly.sdk.aliyun.express.service.CachedExpressQueryService;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Dates;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.JsonMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 天眼数聚 aliyun物流查询实现
 * <p>
 * 阿里云API市场提供方地址：https://market.aliyun.com/products/57126001/cmapi00054243.html?spm=5176.730005.result.1.4c913524ABlUgX&innerSource=search_%E5%BF%AB%E9%80%92%E7%89%A9%E6%B5%81%E6%9F%A5%E8%AF%A2#sku=yuncode4824300001
 * 封装：
 * 1、统一接口对象和返回结构（快递基本信息，快递公司信息，快递轨迹信息，快递状态分类轨迹信息）
 * 2、封装错误消息为异常（code/message）给调用端，统一不同提供商的消息信息，直接可读可用化
 * 3、根据缓存配置，控制缓存策略（单个快递单号多长时间更新一次），降低查询成本
 *
 * @author zhangpu
 * @date 2023-04-09 18:05
 */
@Slf4j
@Component
public class TianYanExpressQueryService extends CachedExpressQueryService implements InitializingBean {


    @Override
    protected ExpressInfo doRequest(String mailNo, String expressCompanyCode, String mobileNo) {
        String path = "/express3";
        AliyunRequest request = new AliyunRequest();
        Map<String, String> params = Maps.newHashMap();
        params.put("number", mailNo);
        if (Strings.isNotBlank(expressCompanyCode)) {
            params.put("type", expressCompanyCode);
        }
        if (Strings.isNotBlank(mobileNo)) {
            params.put("mobile", mobileNo);
        }
        request.setParams(params);
        request.setUrl(path);
        try {
            log.info("request: url:{}, params:{}", request.getUrl(), params);
            AliyunResponse response = transport.request(request);
            String respBody = response.getBody();
            log.info("response: {}", respBody);
            ExpResult expResult = JsonMapper.nonEmptyMapper().fromJson(respBody, ExpResult.class);
            if (!expResult.isSuccess()) {
                // 未成功，则抛出异常，附带错误码和错误信息
                throw new BusinessException(String.valueOf(expResult.getCode()), expResult.getMsg(), "");
            }
            // 转换为通用对象返回
            return convert(expResult);
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException("JSON_RESULT_PARSE_FAIL", "返回报文JSON解析失败", e.getMessage());
        }
    }

    @Override
    protected void doAfterQuery(ExpressInfo expressInfo) {
        List<ExpressTrack> expressTracks = Lists.newArrayList();
        Map<String, Set<ExpressTrack>> expressTrackMap = Maps.newHashMap();
        for (ExpressTrack expressTrack : expressInfo.getExpressTracks()) {
            if (Collections3.isEmpty(expressTrackMap.get(expressTrack.getStatusText()))) {
                expressTrackMap.put(expressTrack.getStatusText(), Sets.newHashSet());
            }
            expressTrackMap.get(expressTrack.getStatusText()).add(expressTrack);
        }
        expressInfo.setExpressTrackMap(expressTrackMap);
    }

    protected ExpressInfo convert(ExpResult result) {
        ExpInfo info = Collections3.getFirst(result.getData().getInfo());
        ExpressInfo expressInfo = new ExpressInfo();
        // 快递基本信息
        expressInfo.setMailNo(info.getMailNo());
        if (Strings.isNotBlank(info.getTheLastTime())) {
            try {
                expressInfo.setLastTime(Dates.parse(info.getTheLastTime()));
            } catch (Exception e) {
                // ignore error throw
                log.warn("日期解析转换错误:{}", e.getMessage());
            }
        }
        expressInfo.setLastMessage(info.getTheLastMessage());
        expressInfo.setTaskTime(info.getTakeTime());
        expressInfo.setLastStatus(info.getLogisticsStatus());
        expressInfo.setLastStatusDesc(info.getLogisticsStatusDesc());
        // 快递公司信息
        ExpressCompany ec = new ExpressCompany();
        ec.setCode(info.getCpCode());
        ec.setName(info.getLogisticsCompanyName());
        ec.setUrl(info.getCpUrl());
        ec.setTel(info.getCpMobile());
        expressInfo.setExpressCompany(ec);
        // 快递轨迹信息
        List<ExpTrack> tracks = info.getLogisticsTraceDetailList();
        if (Collections3.isEmpty(tracks)) {
            return expressInfo;
        }

        List<ExpressTrack> expressTracks = Lists.newArrayList();
        for (ExpTrack track : tracks) {
            expressTracks.add(convert(track));
        }
        expressInfo.setExpressTracks(expressTracks);
        return expressInfo;
    }


    /**
     * 轨迹对象转换
     *
     * @param track
     * @return
     */
    protected ExpressTrack convert(ExpTrack track) {
        ExpressTrack expressTrack = new ExpressTrack();
        expressTrack.setAreaCode(track.getAreaCode());
        expressTrack.setAreaName(track.getAreaName());
        expressTrack.setStatus(track.getLogisticsStatus());
        expressTrack.setStatusText(ExpStatusEnum.find(track.getLogisticsStatus()).message());
        expressTrack.setSubStatus(track.getSubLogisticsStatus());
        expressTrack.setSubStatusText(ExpSubStatusEnum.find(track.getSubLogisticsStatus()).message());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(track.getTime());
        expressTrack.setStatusTime(calendar.getTime());
        expressTrack.setDesc(track.getDesc());
        return expressTrack;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        // 设置天眼数聚在阿里云API市场的网关地址
        transport.setGateway("https://express3.market.alicloudapi.com");
    }
}
