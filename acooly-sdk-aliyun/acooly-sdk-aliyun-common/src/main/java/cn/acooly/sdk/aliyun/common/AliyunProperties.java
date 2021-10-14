/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-13 23:12
 */
package cn.acooly.sdk.aliyun.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static cn.acooly.sdk.aliyun.common.AliyunProperties.PREFIX;

/**
 * 阿里云及市场通用配置
 *
 * @author zhangpu
 * @date 2021-10-13 23:12
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = PREFIX)
public class AliyunProperties {
    public static final String PREFIX = "acooly.sdk.aliyun";
    public boolean enable;

    private String appCode;
    private String appKey;
    private String appSecret;

}
