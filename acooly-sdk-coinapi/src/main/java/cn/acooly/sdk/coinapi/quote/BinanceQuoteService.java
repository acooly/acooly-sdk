/**
 * acooly-sdk
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-25 15:48
 */
package cn.acooly.sdk.coinapi.quote;

import cn.acooly.sdk.coinapi.CoinApiProperties;
import cn.acooly.sdk.coinapi.enums.CoinApiErrors;
import cn.acooly.sdk.coinapi.explorer.ExplorerCacheManager;
import cn.acooly.sdk.coinapi.quote.dto.CoinQuote;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.BigMoney;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 币按交易所报价
 *
 * @author zhangpu
 * @date 2021-12-25 15:48
 */
@Slf4j
@NoArgsConstructor
@Component
public class BinanceQuoteService {

    @Autowired
    private CoinApiProperties coinApiProperties;

    /**
     * 与USDT的兑换价格
     *
     * @param coinSymbol
     * @return
     */
    public CoinQuote quoteUsdt(String coinSymbol) {
        return quote(coinSymbol + "USDT");
    }

    public CoinQuote quote(String coinFrom, String coinTo) {
        return quote(coinFrom + coinTo);
    }

    public CoinQuote quote(String symbol) {
        String url = "https://api.binance.com/api/v3/ticker/price?symbol=" + symbol;
        int timeout = coinApiProperties.getExplorer().getTimeoutSeconds() * 1000;
        try {
            String body = HttpRequest.get(url).connectTimeout(timeout).readTimeout(timeout).body();
            JSONObject jsonObject = JSON.parseObject(body);
            CoinQuote coinQuote = new CoinQuote();
            coinQuote.setSymbol(symbol);
            coinQuote.setPrice(BigMoney.valueOf(jsonObject.getString("price")));
            return coinQuote;
        } catch (Exception e) {
            log.warn("Binance Qutote 获取失败: {}", e.getMessage());
            throw new BusinessException(CoinApiErrors.DATA_PARSE_ERROR, "Binance Qutote 获取失败");
        }
    }

    public BinanceQuoteService(CoinApiProperties coinApiProperties) {
        this.coinApiProperties = coinApiProperties;
    }

    public void setCoinApiProperties(CoinApiProperties coinApiProperties) {
        this.coinApiProperties = coinApiProperties;
    }
}
