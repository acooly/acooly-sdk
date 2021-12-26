/**
 * acooly-sdk
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-25 16:02
 */
package cn.acooly.sdk.coinapi;

import cn.acooly.sdk.coinapi.platform.binance.BinanceQuoteService;
import cn.acooly.sdk.coinapi.platform.binance.dto.BinanceCoinQuote;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * Binance交易所报价API测试
 * 注意：需要代理
 *
 * @author zhangpu
 * @date 2021-12-25 16:02
 */
@Slf4j
public class BinanceQuoteServiceTest {

    BinanceQuoteService binanceQuoteService;
    CoinApiProperties coinApiProperties = new CoinApiProperties();

    String PROXY_HOST = "127.0.0.1";
    int PROXY_PORT = 19180;

    @Before
    public void init() {

        System.setProperty("https.proxySet", "true");
        System.setProperty("https.proxyHost", PROXY_HOST);
        System.setProperty("https.proxyPort", String.valueOf(PROXY_PORT));

        binanceQuoteService = new BinanceQuoteService();
        binanceQuoteService.setCoinApiProperties(coinApiProperties);
    }

    @Test
    public void testQuoteUsdt() {
        long start = System.currentTimeMillis();
        BinanceCoinQuote filQuote = null;
        try {
            filQuote = binanceQuoteService.quoteUsdt("FIL");
        } finally {
            log.info("FIL Quote: {} , times: {} ms", filQuote, System.currentTimeMillis() - start);
        }


    }

}
