package cn.acooly.sdk.coinapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpu@acooly.cn
 */
@Configuration
@EnableConfigurationProperties({CoinApiProperties.class})
@ConditionalOnProperty(value = "acooly.sdk.coinapi.enable", matchIfMissing = true)
@ComponentScan(basePackages = "cn.acooly.sdk.coinapi")
public class CoinApiAutoConfig {

    @Autowired
    private CoinApiProperties coinApiProperties;


}
