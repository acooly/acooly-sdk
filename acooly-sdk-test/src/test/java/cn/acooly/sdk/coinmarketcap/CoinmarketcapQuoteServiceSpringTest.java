/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-26 21:35
 */
package cn.acooly.sdk.coinmarketcap;

import cn.acooly.sdk.coinapi.platform.coinmarketcap.CoinmarketcapQuoteScheduler;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.CoinmarketcapQuoteService;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.entity.CoinmarketcapQuotes;
import cn.acooly.sdk.test.NoWebTestBase;
import com.acooly.core.utils.Dates;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author zhangpu
 * @date 2021-12-26 21:35
 */
@Slf4j
public class CoinmarketcapQuoteServiceSpringTest extends NoWebTestBase {

    @Autowired
    private CoinmarketcapQuoteService coinmarketcapQuoteService;


    @Before
    public void init() {
        // 代理参数设置
        System.setProperty("https.proxySet", "true");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "19180");
    }

    @Test
    public void testLatest() {
        String coinCode = "BTC";
        CoinmarketcapQuotes coinmarketcapQuotes = coinmarketcapQuoteService.latest(coinCode);
        log.info("latest quotes: coin:{} : {}", coinCode, coinmarketcapQuotes);
    }

    @Test
    public void testQuery() {
        String coinCode = "BTC";
        Date startTime = Dates.parse("2021-12-26 22:00:00");
        Date endTime = Dates.parse("2021-12-26 22:40:00");
        List<CoinmarketcapQuotes> coinmarketcapQuotesList = coinmarketcapQuoteService
                .query(coinCode, startTime, endTime);
        log.info("query quotes: coin:{} : {} : {}", coinCode, coinmarketcapQuotesList.size(),
                coinmarketcapQuotesList);
    }

}
