package cn.acooly.sdk.openai;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpu@acooly.cn
 */
@Configuration
@EnableConfigurationProperties({OpenAiSdkProperties.class})
@ConditionalOnProperty(value = OpenAiSdkProperties.PREFIX + ".enable", matchIfMissing = true)
public class OpenAiSdkAutoConfig {

}
