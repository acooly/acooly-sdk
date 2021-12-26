/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-25 22:52
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap;

import cn.acooly.sdk.coinapi.platform.coinmarketcap.sdk.CoinmarketcapSdk;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.sdk.message.CoinmarketcapQuoteInfo;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.entity.CoinmarketcapQuotes;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.service.CoinmarketcapQuotesManager;
import com.acooly.core.utils.mapper.BeanCopier;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

/**
 * 币报价调度
 * <p>
 * 通过API定时拉取提供商的币报价数据，存入数据库
 *
 * @author zhangpu
 * @date 2021-12-25 22:52
 */
@Slf4j
@PropertySource(value = {"application-${spring.profiles.active}.properties"})
public class CoinmarketcapQuoteScheduler {

    @Autowired
    private CoinmarketcapSdk coinmarketcapSdk;

    @Autowired
    private CoinmarketcapQuotesManager coinmarketcapQuotesManager;

    /**
     * 拉取数据
     */
    @Scheduled(fixedDelayString = "${acooly.sdk.coinapi.coinmarketcap.scheduleInterval:1800000}", initialDelay = 1000)
    public void pull() {
        // API拉取
        Map<String, CoinmarketcapQuoteInfo> quotes = coinmarketcapSdk.quotesLatest(Lists.newArrayList("BTC", "ETH", "FIL"), "USD");
        if (quotes == null && quotes.size() == 0) {
            return;
        }
        for (CoinmarketcapQuoteInfo quoteInfo : quotes.values()) {
            CoinmarketcapQuotes coinmarketcapQuotes = BeanCopier.copy(quoteInfo, CoinmarketcapQuotes.class);
            saveIgnoreError(coinmarketcapQuotes);
        }
    }

    protected void saveIgnoreError(CoinmarketcapQuotes coinmarketcapQuotes) {
        try {
            coinmarketcapQuotesManager.save(coinmarketcapQuotes);
            log.info("DbCache Saved: {}", coinmarketcapQuotes);
        } catch (Exception e) {
            // ig
        }
    }

}
