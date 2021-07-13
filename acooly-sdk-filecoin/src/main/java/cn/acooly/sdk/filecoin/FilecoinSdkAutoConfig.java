package cn.acooly.sdk.filecoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpu@acooly.cn
 */
@Configuration
@EnableConfigurationProperties({FilecoinSdkProperties.class})
@ConditionalOnProperty(value = "acooly.sdk.filecoin.enable", matchIfMissing = true)
@ComponentScan(basePackages = "cn.acooly.sdk.filecoin")
public class FilecoinSdkAutoConfig {

    @Autowired
    private FilecoinSdkProperties filecoinSdkProperties;


}
