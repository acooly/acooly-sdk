/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-26 19:19
 */
package cn.acooly.sdk.coinmarketcap;

import cn.acooly.sdk.coinapi.CoinApiProperties;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.sdk.CoinmarketcapSdk;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.sdk.message.CoinmarketcapQuoteInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-12-26 19:19
 */
@Slf4j
public class CoinmarketcapSdkTest {

    private CoinApiProperties coinApiProperties = new CoinApiProperties();

    private CoinmarketcapSdk coinmarketcapSdk;

    @Before
    public void init() {
        coinmarketcapSdk = new CoinmarketcapSdk(coinApiProperties);

        // 代理参数设置
        System.setProperty("https.proxySet", "true");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "19180");
    }

    @Test
    public void testQuotesLatest() {
        List<String> coinCodes = Lists.newArrayList();
        coinCodes.add("BTC");
        coinCodes.add("ETH");
        Map<String, CoinmarketcapQuoteInfo> quotes = coinmarketcapSdk.quotesLatest(coinCodes, "USD");
        for (CoinmarketcapQuoteInfo quote : quotes.values()) {
            log.info("{}", quote);
        }
    }

}
