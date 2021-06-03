package cn.acooly.sdk.coinrate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpu@acooly.cn
 */
@Configuration
@EnableConfigurationProperties({CoinRateProperties.class})
@ConditionalOnProperty(value = "acooly.sdk.coinrate.enable", matchIfMissing = true)
@ComponentScan(basePackages = "cn.acooly.sdk.coinrate")
public class CoinRateAutoConfig {

    @Autowired
    private CoinRateProperties coinRateProperties;


}
