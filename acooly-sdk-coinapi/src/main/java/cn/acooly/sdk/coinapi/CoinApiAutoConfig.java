package cn.acooly.sdk.coinapi;

import cn.acooly.sdk.coinapi.platform.coinmarketcap.CoinmarketcapQuoteScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpu@acooly.cn
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({CoinApiProperties.class})
@ConditionalOnProperty(value = "acooly.sdk.coinapi.enable", matchIfMissing = true)
@ComponentScan(basePackages = "cn.acooly.sdk.coinapi")
public class CoinApiAutoConfig {

    @Autowired
    private CoinApiProperties coinApiProperties;


    @Bean
    @ConditionalOnProperty(value = "acooly.sdk.coinapi.coinmarketcap.scheduleEnable", matchIfMissing = false)
    public CoinmarketcapQuoteScheduler coinQuoteScheduler() {
        CoinmarketcapQuoteScheduler coinmarketcapQuoteScheduler = new CoinmarketcapQuoteScheduler();
        log.info("启动coinmarketcap定时拉取报价数据任务，调度周期: {}s/次", coinApiProperties.getCoinmarketcap().getScheduleInterval() / 1000);
        return coinmarketcapQuoteScheduler;
    }


}
