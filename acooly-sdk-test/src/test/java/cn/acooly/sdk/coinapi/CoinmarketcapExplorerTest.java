/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-21 13:50
 */
package cn.acooly.sdk.coinapi;

import cn.acooly.sdk.coinapi.explorer.dto.BlockchairBitcoin;
import cn.acooly.sdk.swft.SwftProperties;
import cn.acooly.sdk.swft.SwftSdkService;
import cn.acooly.sdk.swft.transport.HttpSwftTransport;
import cn.acooly.sdk.swft.transport.SwftTransport;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2021-12-21 13:50
 */
@Slf4j
public class CoinmarketcapExplorerTest {

    String PROXY_HOST = "127.0.0.1";
    int PROXY_PORT = 19180;

    @Before
    public void init() {
        System.setProperty("https.proxySet", "true");
        System.setProperty("https.proxyHost", PROXY_HOST);
        System.setProperty("https.proxyPort", String.valueOf(PROXY_PORT));
    }

    @Test
    public void testBlcokchairStats() {
        String url = "https://api.blockchair.com/bitcoin/stats";
        String body = HttpRequest.get(url).body();

        JSONObject jsonObject =  JSON.parseObject(body);
        JSONObject jsonData = (JSONObject) jsonObject.get("data");
        if(jsonData != null){
            BlockchairBitcoin blockchairBitcoin = jsonData.toJavaObject(BlockchairBitcoin.class);
            System.out.println(blockchairBitcoin);
        }


    }


    @Test
    public void testSimple() {
        String url = "https://block.cc/coin/bitcoin/";
        String body = HttpRequest.get(url).body();
        System.out.println(body);
    }

    @Test
    public void homePage() throws Exception {
        try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX, PROXY_HOST, PROXY_PORT)) {

            //ajax
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            //支持js
            webClient.getOptions().setJavaScriptEnabled(true);
            //忽略js错误
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            //忽略css错误
            webClient.setCssErrorHandler(new SilentCssErrorHandler());
            //不执行CSS渲染
            webClient.getOptions().setCssEnabled(false);
            //超时时间
            webClient.getOptions().setTimeout(3000);
            //允许重定向
            webClient.getOptions().setRedirectEnabled(true);
            //允许cookie
            webClient.getCookieManager().setCookiesEnabled(true);

            final HtmlPage page = webClient.getPage("https://blockchain.coinmarketcap.com/zh/chain/bitcoin");
            final String pageAsText = page.asNormalizedText();
            System.out.println(pageAsText);
        }
    }
}
