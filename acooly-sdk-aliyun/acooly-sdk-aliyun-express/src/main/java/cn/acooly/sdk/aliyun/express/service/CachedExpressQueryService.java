/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-09 16:29
 */
package cn.acooly.sdk.aliyun.express.service;

import cn.acooly.sdk.aliyun.common.transport.HttpTransport;
import cn.acooly.sdk.aliyun.express.AliyunExpressProperties;
import cn.acooly.sdk.aliyun.express.domain.ExpressInfo;
import com.acooly.core.utils.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangpu
 * @date 2023-04-09 16:29
 */
@Slf4j
public abstract class CachedExpressQueryService implements ExpressQueryService {

    public static final String CACHE_KEY_PREFIX = CachedExpressQueryService.class.getName();
    public static final String SF_EXPRESS_PREFIX = "SF";

    @Autowired
    protected RedisTemplate<String, ExpressInfo> redisTemplate;

    @Autowired
    protected AliyunExpressProperties aliyunExpressProperties;

    @Resource(name = "aliyunExpressTransport")
    protected HttpTransport transport;

    @Override
    public ExpressInfo query(String mailNo, String expressCompanyCode, String mobileNo) {
        // 从缓存获取快递信息
        ExpressInfo expressInfo = getCache(mailNo, expressCompanyCode, mobileNo);
        // 如果不存在，则请求快递查询
        if (expressInfo == null) {
            expressInfo = doRequest(mailNo, expressCompanyCode, mobileNo);
            // 如果查询成功，则设置缓存
            if (expressInfo != null) {
                setCache(mailNo, expressCompanyCode, expressInfo, mobileNo);
            }
        }
        doAfterQuery(expressInfo);
        return expressInfo;
    }

    /**
     * 查询（含缓存）后的后置处理
     *
     * @param expressInfo
     */
    protected void doAfterQuery(ExpressInfo expressInfo) {

    }

    /**
     * 请求远程API快递查询
     *
     * @param mailNo
     * @param expressCompanyCode
     * @param mobileNo
     * @return
     */
    protected abstract ExpressInfo doRequest(String mailNo, String expressCompanyCode, String mobileNo);

    protected ExpressInfo getCache(String mailNo, String expressCompanyCode, String mobileNo) {
        return redisTemplate.opsForValue().get(getCacheKey(mailNo, expressCompanyCode, mobileNo));
    }

    protected void setCache(String mailNo, String expressCompanyCode, ExpressInfo expressInfo, String mobileNo) {
        String key = getCacheKey(mailNo, expressCompanyCode, mobileNo);
        redisTemplate.opsForValue().set(key, expressInfo,
                aliyunExpressProperties.getCache().getTimeout(),
                TimeUnit.SECONDS);
    }


    /**
     * 获取缓存KEY
     *
     * @param mailNo
     * @param expressCompanyCode
     * @return
     */
    protected String getCacheKey(String mailNo, String expressCompanyCode, String mobileNo) {
        String key = CACHE_KEY_PREFIX + "." + Strings.trimToEmpty(expressCompanyCode) + "." + mailNo;
        if (Strings.startsWithIgnoreCase(mailNo, SF_EXPRESS_PREFIX) && Strings.isNotEmpty(mobileNo)) {
            /** 顺丰单号"SF"开头；顺丰采用“mailNo+mobileNo”模式,存在手机号码输入错误,无法查询物流,输入正确可查询 */
            key += "." + mobileNo;
        }
        return key;
    }

}
