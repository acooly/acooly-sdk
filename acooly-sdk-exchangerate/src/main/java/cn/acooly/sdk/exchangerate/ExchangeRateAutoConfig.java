package cn.acooly.sdk.exchangerate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpu@acooly.cn
 */
@Configuration
@EnableConfigurationProperties({ExchangeRateProperties.class})
@ConditionalOnProperty(value = "acooly.sdk.exchangerate.enable", matchIfMissing = true)
public class ExchangeRateAutoConfig {

    @Autowired
    private ExchangeRateProperties exchangeRateProperties;


}
