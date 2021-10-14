package cn.acooly.sdk.aliyun.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpu@acooly.cn
 */
@Configuration
@EnableConfigurationProperties({AliyunProperties.class})
@ConditionalOnProperty(value = AliyunProperties.PREFIX + ".enable", matchIfMissing = true)
public class AliyunAutoConfig {

}
