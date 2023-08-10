/*
 * www.yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * qiubo@yiji.com 2017-02-24 21:59 创建
 */
package cn.acooly.sdk.aliyun.content.security;

import cn.acooly.sdk.aliyun.common.config.AliyunMarketConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author zhangpu@acooly.cn
 */
@ConfigurationProperties(prefix = ContentSecurityProperties.PREFIX)
@Data
@Slf4j
@Validated
public class ContentSecurityProperties extends AliyunMarketConfig {
    public static final String PREFIX = "acooly.sdk.aliyun.content.security";
    public static final String GATEWAY_DEFAULT = "http://green.cn-shanghai.aliyuncs.com/green/text/scan";
    public static final String REGION_ID_DEFAULT = "cn-shanghai";
    /**
     * 组件开关
     */
    private Boolean enable = true;
    /**
     * 是否开启mock
     */
    private Boolean mock = false;

    public ContentSecurityProperties() {
        setGateway(GATEWAY_DEFAULT);
        setRegionId(REGION_ID_DEFAULT);
    }
}
