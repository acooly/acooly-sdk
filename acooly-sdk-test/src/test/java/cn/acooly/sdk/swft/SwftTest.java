/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-16 14:26
 */
package cn.acooly.sdk.swft;

import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangpu
 * @date 2021-09-16 14:26
 */
@Slf4j
public class SwftTest {

    private String host = "https://www.swftc.info";

    @Test
    public void testQueryCoinList() throws Exception {

        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"supportType\":\"advanced\"}");
        Request request = new Request.Builder()
                .url(host + "/api/v1/queryCoinList")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
        Response response = client.newCall(request).execute();
        log.info("response: {}", response);
        log.info("response body: {}", response.body().string());
    }

    @Test
    public void testQueryCoinList2() throws Exception {

        HttpRequest httpRequest = HttpRequest.post(host + "/api/v1/queryCoinList")
                .contentType(HttpRequest.CONTENT_TYPE_JSON)
                .useCaches(false)
                .connectTimeout(60 * 1000)
                .readTimeout(60 * 1000)
                .send("{\"supportType\":\"advanced\"}");

        log.info("response: {}", httpRequest.code());
        log.info("response body: {}", httpRequest.body());
    }

    @Test
    public void testGetBaseInfo() throws Exception {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"depositCoinCode\":\"BTC\",\"receiveCoinCode\":\"ETH\"}");
        Request request = new Request.Builder()
                .url(host + "/api/v1/getBaseInfo")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
        Response response = client.newCall(request).execute();
        log.info("response: {}", response);
        log.info("response body: {}", response.body().string());
    }
}
