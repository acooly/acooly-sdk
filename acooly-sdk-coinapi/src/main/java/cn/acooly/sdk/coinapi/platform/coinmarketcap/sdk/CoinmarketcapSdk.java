/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-25 23:14
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap.sdk;

import cn.acooly.sdk.coinapi.CoinApiProperties;
import cn.acooly.sdk.coinapi.enums.CoinApiErrors;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.sdk.message.CoinmarketcapQuoteInfo;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.sdk.message.CoinmarketcapResponse;
import com.acooly.core.common.exception.BusinessException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-12-25 23:14
 */
@Slf4j
@Component
@NoArgsConstructor
public class CoinmarketcapSdk {

    @Autowired
    private CoinApiProperties coinApiProperties;

    /**
     * 最新报价查询
     *
     * @param coinCodes
     * @param convert
     */
    public Map<String, CoinmarketcapQuoteInfo> quotesLatest(List<String> coinCodes, String convert) {

        // 临时代理参数设置
        System.setProperty("https.proxySet", "true");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "19180");

        String endpoints = "/v1/cryptocurrency/quotes/latest";
        Map<String, String> params = Maps.newHashMap();
        String symbol = Strings.join(coinCodes.iterator(), ',');
        params.put("symbol", symbol);
        params.put("convert", convert);
        String body = send(endpoints, params);
        CoinmarketcapResponse response = JSON.parseObject(body, CoinmarketcapResponse.class);
        if (!response.success()) {
            throw new BusinessException(CoinApiErrors.GATEWAY_SERVER_ERROR, response.getStatus().getErrorMessage());
        }
        String dataJson = response.getData();
        Map<String, CoinmarketcapQuoteInfo> map = Maps.newHashMap();
        JSONObject jsonMap = JSON.parseObject(dataJson);
        for (Object key : jsonMap.keySet()) {
            JSONObject v = (JSONObject) jsonMap.get(key);
            JSONObject quoteObject = v.getJSONObject("quote").getJSONObject(convert);
            CoinmarketcapQuoteInfo quote = quoteObject.toJavaObject(CoinmarketcapQuoteInfo.class);
            quote.setCoinCode((String) key);
            if (quote.getLastUpdated() != null) {
                quote.setLastUpdatedMillis(quote.getLastUpdated().getTime());
            }
            map.put((String) key, quote);
        }
        return map;
    }


    protected String send(String endpoint, Map<String, String> params) {
        String gateway = coinApiProperties.getCoinmarketcap().getUrl();
        String apiKey = coinApiProperties.getCoinmarketcap().getSecretKey();
        log.info("Requ {} : {}", endpoint, params);
        String body = HttpRequest.get(gateway + endpoint, params, true)
                .connectTimeout(coinApiProperties.getCoinmarketcap().getConnTimeoutMillis())
                .readTimeout(coinApiProperties.getCoinmarketcap().getReadTimeoutMillis())
                .header("X-CMC_PRO_API_KEY", apiKey).header("Accept", "application/json").body();
        log.info("Resp {} : {}", endpoint, body);
        return body;
    }

    public CoinmarketcapSdk(CoinApiProperties coinApiProperties) {
        this.coinApiProperties = coinApiProperties;
    }
}
