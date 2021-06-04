/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-04 00:15
 */
package cn.acooly.sdk.coinapi.service.impl;

import cn.acooly.sdk.coinapi.dto.MifengchaErrorResult;
import cn.acooly.sdk.coinapi.dto.MifengchaTicker;
import cn.acooly.sdk.coinapi.dto.Ticker;
import cn.acooly.sdk.coinapi.enums.CoinApiErrors;
import cn.acooly.sdk.coinapi.enums.CoinApiProvider;
import cn.acooly.sdk.coinapi.enums.CoinMarket;
import cn.acooly.sdk.coinapi.enums.DigitCurrency;
import cn.acooly.sdk.coinapi.service.CoinApiService;
import cn.acooly.sdk.coinapi.service.CoinApiServiceAbstractImpl;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Asserts;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Strings;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 蜜蜂查商业API 实现
 * https://data.mifengcha.com/
 * <p>
 * 价格：
 * <li>体验版：10,000次/月，1次/秒 免费</li>
 * <li>基础版：300,000次/月，4次/秒 159元/月</li>
 * <li>专业版：900,000次/月，8次/秒 399元/月</li>
 * <li>企业版：4000,000次/月，8次/秒 2079元/月</li>
 * </p>
 *
 * @author zhangpu
 * @date 2021-06-04 00:15
 */
@Slf4j
@Component
public class MifengchaCoinApiService extends CoinApiServiceAbstractImpl implements CoinApiService {

    @Override
    protected Ticker doTicker(String symbol) {
        Asserts.notEmpty(coinApiProperties.getMifengcha().getUrl(), "网关地址");
        Asserts.notEmpty(coinApiProperties.getMifengcha().getAccessKey(), "ApiKey");
        String requestUrl = coinApiProperties.getMifengcha().getUrl()
                + "/v3/tickers?api_key=" + coinApiProperties.getMifengcha().getAccessKey()
                + "&market_pair=" + buildMarketPair(symbol);
        // 通讯
        String resp = null;
        try {
            resp = HttpRequest.get(requestUrl).contentType(HttpRequest.CONTENT_TYPE_JSON)
                    .connectTimeout(coinApiProperties.getTianapi().getConnTimeout() * 1000)
                    .readTimeout(coinApiProperties.getTianapi().getReadTimeout() * 1000)
                    .body();
        } catch (Exception e) {
            log.warn("CoinApi.ticker [{}] ERROR:{}, detail:{}", this.getName(),
                    CoinApiErrors.NETWORK_ERROR.message(), e.getMessage());
            throw new BusinessException(CoinApiErrors.NETWORK_ERROR, e);
        }

        // 解析
        if (!isSuccess(resp)) {
            MifengchaErrorResult result = jsonMapper.fromJson(resp, MifengchaErrorResult.class);
            log.warn("CoinApi.ticker [{}] ERROR:{}", this.getName(), result);
            throw new BusinessException(result.getCode(), result.getMessage(), "");
        }

        List<MifengchaTicker> result = jsonMapper.fromJson(resp, jsonMapper.createCollectionType(List.class, MifengchaTicker.class));
        log.info("CoinApi.ticker [{}] : {}", this.getName(), result);
        return convert(Collections3.getFirst(result));
    }


    private Ticker convert(MifengchaTicker mifengchaTicker) {
        Ticker ticker = new Ticker();
        ticker.setTime(new Date(mifengchaTicker.getTimestamp()));
        ticker.setBaseVolume(mifengchaTicker.getBaseVolume());
        ticker.setQuoteVolume(mifengchaTicker.getQuoteVolume());
        ticker.setLastPrice(mifengchaTicker.getLastPrice());
        ticker.setHighPrice(mifengchaTicker.getHighPrice());
        ticker.setLowPrice(mifengchaTicker.getLowPrice());
        ticker.setOpenPrice(mifengchaTicker.getOpenPrice());
        ticker.setSymbol(mifengchaTicker.getMarketPair());
        return ticker;
    }


    protected String buildMarketPair(String symbol) {
        String from = null;
        for (DigitCurrency currency : DigitCurrency.values()) {
            if (symbol.startsWith(currency.code())) {
                from = currency.code();
            }
        }
        if (Strings.isBlank(from)) {
            log.warn("不支持的币种符号:{}", symbol);
            throw new BusinessException(CommonErrorCodes.UNSUPPORTED_ERROR, "不支持的币种符号:" + symbol);
        }
        String to = Strings.upperCase(Strings.substringAfter(symbol, from));
        from = Strings.upperCase(from);

        List<String> pairs = Lists.newArrayList();
        for (CoinMarket coinMarket : CoinMarket.values()) {
            pairs.add(coinMarket.code() + "_" + from + "_" + to);
        }
        String marketPair = Strings.joinWith(",", pairs.toArray(new String[]{}));
        return marketPair;
    }

    @Override
    public String getName() {
        return CoinApiProvider.mifengcha.code();
    }

    @Override
    public int getOrder() {
        return CoinApiProvider.mifengcha.ordinal();
    }

    private boolean isSuccess(String text) {
        return Strings.startsWith(text, "[");
    }

}
