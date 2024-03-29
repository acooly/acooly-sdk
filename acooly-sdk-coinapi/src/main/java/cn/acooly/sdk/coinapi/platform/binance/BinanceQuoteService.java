/**
 * acooly-sdk
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-25 15:48
 */
package cn.acooly.sdk.coinapi.platform.binance;

import cn.acooly.sdk.coinapi.CoinApiProperties;
import cn.acooly.sdk.coinapi.enums.CoinApiErrors;
import cn.acooly.sdk.coinapi.platform.binance.dto.BinanceCoinQuote;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.BigMoney;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public BinanceCoinQuote quoteUsdt(String coinSymbol) {
        return quote(coinSymbol + "USDT");
    }

    public BinanceCoinQuote quote(String coinFrom, String coinTo) {
        return quote(coinFrom + coinTo);
    }

    public BinanceCoinQuote quote(String symbol) {
        String url = "https://api.binance.com/api/v3/ticker/price?symbol=" + symbol;
        int timeout = coinApiProperties.getExplorer().getTimeoutSeconds() * 1000;
        try {
            String body = HttpRequest.get(url).connectTimeout(timeout).readTimeout(timeout).body();
            JSONObject jsonObject = JSON.parseObject(body);
            BinanceCoinQuote binanceCoinQuote = new BinanceCoinQuote();
            binanceCoinQuote.setSymbol(symbol);
            binanceCoinQuote.setPrice(BigMoney.valueOf(jsonObject.getString("price")));
            return binanceCoinQuote;
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
