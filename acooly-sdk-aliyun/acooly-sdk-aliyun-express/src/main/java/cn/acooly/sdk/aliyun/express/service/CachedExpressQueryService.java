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

    @Autowired
    protected RedisTemplate<String, ExpressInfo> redisTemplate;

    @Autowired
    protected AliyunExpressProperties aliyunExpressProperties;

    @Resource(name = "aliyunExpressTransport")
    protected HttpTransport transport;

    @Override
    public ExpressInfo query(String mailNo, String expressCompanyCode, String mobileNo) {
        // 从缓存获取快递信息
        ExpressInfo expressInfo = getCache(mailNo, expressCompanyCode);
        // 如果不存在，则请求快递查询
        if (expressInfo == null) {
            expressInfo = doRequest(mailNo, expressCompanyCode, mobileNo);
            // 如果查询成功，则设置缓存
            if (expressInfo != null) {
                setCache(mailNo, expressCompanyCode, expressInfo);
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

    protected ExpressInfo getCache(String mailNo, String expressCompanyCode) {
        return redisTemplate.opsForValue().get(getCacheKey(mailNo, expressCompanyCode));
    }

    protected void setCache(String mailNo, String expressCompanyCode, ExpressInfo expressInfo) {
        String key = getCacheKey(mailNo, expressCompanyCode);
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
    protected String getCacheKey(String mailNo, String expressCompanyCode) {
        return CACHE_KEY_PREFIX + "." + Strings.trimToEmpty(expressCompanyCode) + "." + mailNo;
    }

}
