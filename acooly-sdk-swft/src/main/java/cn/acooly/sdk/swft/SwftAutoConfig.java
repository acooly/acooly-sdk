package cn.acooly.sdk.swft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpu@acooly.cn
 */
@Configuration
@EnableConfigurationProperties({SwftProperties.class})
@ConditionalOnProperty(value = SwftProperties.PREFIX + ".enable", matchIfMissing = true)
public class SwftAutoConfig {

}
