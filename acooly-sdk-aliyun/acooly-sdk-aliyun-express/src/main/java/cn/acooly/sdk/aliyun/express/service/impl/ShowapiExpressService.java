/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-14 11:03
 */
package cn.acooly.sdk.aliyun.express.service.impl;

import cn.acooly.sdk.aliyun.common.transport.AliyunRequest;
import cn.acooly.sdk.aliyun.common.transport.AliyunResponse;
import cn.acooly.sdk.aliyun.common.transport.HttpTransport;
import cn.acooly.sdk.aliyun.express.AliyunExpressProperties;
import cn.acooly.sdk.aliyun.express.ExpressConstants;
import cn.acooly.sdk.aliyun.express.dto.ExpressTrackInfo;
import cn.acooly.sdk.aliyun.express.dto.ExpressTrackResult;
import cn.acooly.sdk.aliyun.express.enums.CommonExpComp;
import cn.acooly.sdk.aliyun.express.service.ExpressService;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.JsonMapper;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 快递相关API服务
 * <p>
 * aliyun中易元数据实现
 *
 * @author zhangpu
 * @date 2021-10-14 11:03
 */
@Slf4j
@Component
public class ShowapiExpressService implements ExpressService {

    public static final String CACHE_KEY_PREFIX = ShowapiExpressService.class.getName();

    @Resource(name = "aliyunExpressTransport")
    private HttpTransport transport;
    @Autowired
    private RedisTemplate<String, ExpressTrackInfo> redisTemplate;
    @Autowired
    private AliyunExpressProperties aliyunExpressProperties;

    @Override
    public ExpressTrackInfo track(@NotBlank String orderNo, String expCompCode, @Null String phonePostfix) {
        String expComp = expCompCode;
        if (Strings.isBlank(expComp)) {
            expComp = "auto";
        }
        // get from cache
        ExpressTrackInfo expressTrackInfo = getCache(orderNo, expComp);
        if (expressTrackInfo != null) {
            log.info("Query from Cache: {}.{}", expComp, orderNo);
            return expressTrackInfo;
        }

        Map<String, String> params = Maps.newHashMap();
        params.put("nu", orderNo);
        params.put("com", expComp);

        if (Strings.equals(expComp, CommonExpComp.shunfeng.code()) && Strings.isBlank(phonePostfix)) {
            throw new BusinessException(CommonErrorCodes.PARAMETER_ERROR, "顺丰速运快递查询必须输入寄/收人手机号码后四位");
        }
        if (Strings.isNotBlank(phonePostfix)) {
            params.put("phone", phonePostfix);
        }
        expressTrackInfo = request(params);
        if (expressTrackInfo != null) {
            log.info("Query from API: {}.{}", expComp, orderNo);
            setCache(orderNo, expComp, expressTrackInfo);
        }
        return expressTrackInfo;
    }

    private ExpressTrackInfo request(Map<String, String> params) {
        String path = "/showapi_expInfo";
        AliyunRequest request = new AliyunRequest();
        request.setParams(params);
        request.setUrl(path);
        AliyunResponse response = transport.request(request);
        String respBody = response.getBody();
        try {
            ExpressTrackResult expressTrackResult = JsonMapper.nonEmptyMapper().fromJson(respBody, ExpressTrackResult.class);
            ExpressTrackInfo expressTrackInfo = expressTrackResult.getExpressTrackInfo();
            expressTrackInfo.setResultText(ExpressConstants.getResultText(expressTrackInfo.getResultCode()));
            expressTrackInfo.setStatusText(ExpressConstants.getStatusText(expressTrackInfo.getStatus()));
            return expressTrackInfo;
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException("JSON_RESULT_PARSE_FAIL", "返回报文JSON解析失败", e.getMessage());
        }
    }


    protected ExpressTrackInfo getCache(String orderNo, String expCompCode) {
        ExpressTrackInfo expressTrackInfo = redisTemplate.opsForValue().get(getCacheKey(orderNo, expCompCode));
        return expressTrackInfo;
    }

    protected void setCache(String orderNo, String expCompCode, ExpressTrackInfo expressTrackInfo) {
        String key = getCacheKey(orderNo, expCompCode);
        redisTemplate.opsForValue().set(key, expressTrackInfo,
                aliyunExpressProperties.getCache().getTimeout(),
                TimeUnit.SECONDS);
    }


    /**
     * 获取缓存KEY
     *
     * @param orderNo
     * @param expCompCode
     * @return
     */
    protected String getCacheKey(String orderNo, String expCompCode) {
        return CACHE_KEY_PREFIX + "." + expCompCode + "." + orderNo;
    }

}
