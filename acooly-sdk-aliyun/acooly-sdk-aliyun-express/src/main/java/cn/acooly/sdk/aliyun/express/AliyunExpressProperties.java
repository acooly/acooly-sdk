/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-13 23:12
 */
package cn.acooly.sdk.aliyun.express;

import cn.acooly.sdk.aliyun.common.config.AliyunMarketConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * 阿里云及市场通用配置
 *
 * @author zhangpu
 * @date 2021-10-13 23:12
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = AliyunExpressProperties.PREFIX)
public class AliyunExpressProperties extends AliyunMarketConfig {
    public static final String PREFIX = "acooly.sdk.aliyun.express";
    public boolean enable;
    /**
     * 物流订单缓存配置
     */
    private Cache cache = new Cache();

    public AliyunExpressProperties() {
        setGateway("https://ali-deliver.showapi.com");
    }


    @Data
    public static class Cache {
        /**
         * 是否开启缓存，默认开启
         */
        private boolean enable = true;
        /**
         * 物流信息缓存时间（单位：s,秒）,默认：120秒
         */
        private int timeout = 120;
    }
}
