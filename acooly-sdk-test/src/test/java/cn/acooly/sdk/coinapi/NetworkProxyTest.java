/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2024-05-03 16:13
 */
package cn.acooly.sdk.coinapi;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.junit.Before;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author zhangpu
 * @date 2024-05-03 16:13
 */
@Slf4j
public class NetworkProxyTest {
    protected String PROXY_HOST = "127.0.0.1";
    protected int PROXY_PORT = 29290;

    @Before
    public void init() {
        String isProxy = "true";
        System.setProperty("https.proxySet", isProxy);
        System.setProperty("https.proxyHost", PROXY_HOST);
        System.setProperty("https.proxyPort", String.valueOf(PROXY_PORT));
        log.info("init Proxy: {}", isProxy);
    }

    protected OkHttpClient getProxyOkHttpClient() {
        // 定义代理服务器的地址和端口
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
        // 创建一个OkHttpClient.Builder，并设置代理
        OkHttpClient client = new OkHttpClient.Builder()
                .proxy(proxy)
                .build();
        return client;
    }
}
