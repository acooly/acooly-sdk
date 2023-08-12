/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-14 09:59
 */
package cn.acooly.sdk.aliyun.common.config;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-10-14 09:59
 */
@Data
public class AliyunMarketConfig implements Serializable {

    /**
     * 应用名称
     */
    private String appTitle;

    /**
     * App编码
     * 唯一标志应用身份，同时可作为简单认证的身份标志
     * 如果没有配置，可复用`acooly.sdk.aliyun.app-code`（推荐）
     *
     * @see cn.acooly.sdk.aliyun.common.AliyunProperties
     */
    @Deprecated
    private String appCode;
    /**
     * 应用AccessKey
     * 如果没有配置，可复用`acooly.sdk.aliyun.app-key`（推荐）
     *
     * @see cn.acooly.sdk.aliyun.common.AliyunProperties
     */
    @Deprecated
    private String appKey;

    /**
     * 应用SecretKey
     * 如果没有配置，可复用`acooly.sdk.aliyun.secret-key`（推荐）
     *
     * @see cn.acooly.sdk.aliyun.common.AliyunProperties
     */
    @Deprecated
    private String appSecret;


    /**
     * 网关地址
     */
    @NotBlank
    private String gateway;

    /**
     * 默认链接超时时间（ms）
     */
    @NotBlank
    private int connTimeout = 10 * 1000;


    /**
     * 默认读取超时时间（ms）
     */
    @NotBlank
    private int readTimeout = 10 * 1000;

    /**
     * 区域ID
     */
    private String regionId;

    /**
     * 其他扩展参数
     */
    private Map<String, String> params = Maps.newHashMap();

}
