/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2024-05-02 20:48
 */
package cn.acooly.sdk.coinapi;

import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author zhangpu
 * @date 2024-05-02 20:48
 */
@Slf4j
public class BtcPoolApiTest {

    String gateway = "https://pool.api.btc.com/v1";
    String access_key = "uhbc8sNhYSGzft045MFFmhxwMNBwYKhfNGu8hQbx";
    String puid = "819182";


    String PROXY_HOST = "127.0.0.1";
    int PROXY_PORT = 29290;

    @Before
    public void init() {
        String isProxy = "true";
        System.setProperty("https.proxySet", isProxy);
        System.setProperty("https.proxyHost", PROXY_HOST);
        System.setProperty("https.proxyPort", String.valueOf(PROXY_PORT));
        log.info("init Proxy: {}", isProxy);
    }

    @Test
    public void testAccountInfo() {
        log.info("testAccountInfo");
        String request = gateway + "/account/info?puid=" + puid + "&access_key=" + access_key;
        String url = "https://pool.api.btc.com/v1/account/info?puid=819182&access_key=uhbc8sNhYSGzft045MFFmhxwMNBwYKhfNGu8hQbx";
        log.info("request: {}", request);
        HttpRequest.proxyHost(PROXY_HOST);
        HttpRequest.proxyPort(PROXY_PORT);
        HttpRequest httpRequest = HttpRequest.get(url);
        log.info("code: {}", httpRequest.code());
        log.info("body: {}", httpRequest.body());
    }

    @Test
    public void testAccountInfoWithOkHttp() throws Exception {
        // 定义代理服务器的地址和端口
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
        // 创建一个OkHttpClient.Builder，并设置代理
        OkHttpClient client = new OkHttpClient.Builder()
                .proxy(proxy)
                .build();

        String url = "https://pool.api.btc.com/v1/account/info?puid=819182&access_key=uhbc8sNhYSGzft045MFFmhxwMNBwYKhfNGu8hQbx";
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String body = response.body().string();
            log.info("code: {}", response.code());
            log.info("body: {}", body);
        } catch (Exception e) {
            log.error("error", e);
        }
    }

}
