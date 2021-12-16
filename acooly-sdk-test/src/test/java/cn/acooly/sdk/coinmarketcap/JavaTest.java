/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-15 15:37
 */
package cn.acooly.sdk.coinmarketcap;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-12-15 15:37
 */
@Slf4j
public class JavaTest {

    static String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    private static String apiKey = "31f2aab7-d120-41f9-8812-8b26245165d9";

    public static void main(String[] args) {

        System.setProperty("https.proxySet", "true");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "19180");

        Map<String, String> params = Maps.newHashMap();
        params.put("start", "1");
        params.put("limit", "5000");
        params.put("convert", "USD");

        String result = HttpRequest.get(uri, params, true)
                .header("X-CMC_PRO_API_KEY", apiKey)
                .header("Accept", "application/json")
                .body();

        System.out.println(result);

    }
}
