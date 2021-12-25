/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-21 09:52
 */
package cn.acooly.sdk.coinmarketcap;

import cn.acooly.sdk.swft.SwftProperties;
import cn.acooly.sdk.swft.SwftSdkService;
import cn.acooly.sdk.swft.transport.HttpSwftTransport;
import cn.acooly.sdk.swft.transport.SwftTransport;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-12-21 09:52
 */
@Slf4j
public class CoinmarketcapSimpleTest {

    static String gateway = "https://pro-api.coinmarketcap.com";
    private static String apiKey = "31f2aab7-d120-41f9-8812-8b26245165d9";

    @Before
    public void init() {
        // 代理参数设置
        System.setProperty("https.proxySet", "true");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "19180");
    }

    @Test
    public void testTTT() {
        Map<String, String> params = Maps.newHashMap();
        params.put("start", "1");
        params.put("limit", "5000");
        params.put("convert", "USD");
        String endpoints = "/v1/cryptocurrency/listings/latest";
        String body = send(endpoints, params);
    }

    @Test
    public void testBlockchainStatistics() {
        String endpoints = "/v1/blockchain/statistics/latest";
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", "BTC");
        String body = send(endpoints, params);
    }



    @Test
    public void testListingsLatest() {
        String endpoints = "/v1/cryptocurrency/listings/latest";
        Map<String, String> params = Maps.newHashMap();
        params.put("convert", "ETH");
        String body = send(endpoints, params);
    }


    @Test
    public void testQuotesLatest() {
        String endpoints = "/v1/cryptocurrency/quotes/latest";
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", "BTC,ETH,FIL");
        params.put("convert", "CNY");
        String body = send(endpoints, params);
    }


    @Test
    public void testQuotesHistorical() {
        String endpoints = "/v1/cryptocurrency/quotes/historical";
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", "BTC");
        params.put("convert", "CNY");
        params.put("time_start","2021-12-01");
        params.put("time_end","2021-12-24");
        params.put("interval","daily");
        String body = send(endpoints, params);
    }


    protected String send(String endpoint, Map<String, String> params) {
        log.info("Requ {} : {}", endpoint, params);
        String body = HttpRequest.get(gateway + endpoint, params, true)
                .header("X-CMC_PRO_API_KEY", apiKey).header("Accept", "application/json").body();
        log.info("Resp {} : {}", endpoint, body);
        return body;
    }
}
