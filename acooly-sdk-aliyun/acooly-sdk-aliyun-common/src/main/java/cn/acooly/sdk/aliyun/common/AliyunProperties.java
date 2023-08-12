/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-13 23:12
 */
package cn.acooly.sdk.aliyun.common;

import cn.acooly.sdk.aliyun.common.config.AliyunMarketConfig;
import com.acooly.core.utils.Strings;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.HttpClientConfig;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
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

    /**
     * 阿里云市场通用认证配置
     * 简单身份认证（AppCode）或 签名认证 （AppKey、AppSecret）
     * https://help.aliyun.com/zh/api-gateway/use-cases/call-apis#section-6u4-xg9-7aa
     *
     * 一般来说，阿里云市场的多个产品都可以使用一套AppCode或秘钥身份，所以，这里可以采用统一配置认证信息
     *
     */

    /**
     * 应用编码
     * 用于检查身份认证
     */
    private String appCode;
    /**
     * 应用Key
     * 用于签名认证
     */
    private String appKey;

    /**
     * 应用Secret
     * 用于签名认证
     */
    private String appSecret;


    public IAcsClient newAcsClient(AliyunMarketConfig aliyunMarketConfig) {
        // 兼容获取appKey
        String appKey = getAppKey();
        if (Strings.isBlank(appKey)) {
            appKey = aliyunMarketConfig.getAppKey();
        }
        if (Strings.isBlank(appKey)) {
            throw new RuntimeException("阿里云appKey未配置, 请在application.properties中配置`acooly.sdk.aliyun.app-key`" + "或者`acooly.sdk.aliyun.content.security.app-key`");
        }

        // 兼容获取appSecret
        String appSecret = getAppSecret();
        if (Strings.isBlank(appSecret)) {
            appSecret = aliyunMarketConfig.getAppSecret();
        }
        if (Strings.isBlank(appSecret)) {
            throw new RuntimeException("阿里云appSecret未配置, 请在application.properties中配置`acooly.sdk.aliyun.app-secret`" + "或者`acooly.sdk.aliyun.content.security.app-secret`");
        }

        // 创建客户端对象
        IClientProfile profile = DefaultProfile.getProfile(aliyunMarketConfig.getRegionId(), appKey, appSecret);
        HttpClientConfig httpClientConfig = profile.getHttpClientConfig();
        return new DefaultAcsClient(profile);
    }

}
