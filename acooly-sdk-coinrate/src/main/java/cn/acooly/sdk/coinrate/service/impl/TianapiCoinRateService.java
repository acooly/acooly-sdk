/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-03 14:39
 */
package cn.acooly.sdk.coinrate.service.impl;

import cn.acooly.sdk.coinrate.CoinRateProperties;
import cn.acooly.sdk.coinrate.dto.TianapiResult;
import cn.acooly.sdk.coinrate.dto.Ticker;
import cn.acooly.sdk.coinrate.enums.CoinRateProvider;
import cn.acooly.sdk.coinrate.service.CoinRateService;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.mapper.JsonMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * 币行情天行数据实现
 *
 * @author zhangpu
 * @date 2021-06-03 14:39
 */
@Slf4j
@Component
public class TianapiCoinRateService implements CoinRateService {

    @Autowired
    private CoinRateProperties coinRateProperties;

    private int cacheTimeoutSeconds = 2 * 60;
    Cache<String, Ticker> cache = Caffeine.newBuilder()
            .expireAfterWrite(this.cacheTimeoutSeconds, TimeUnit.SECONDS)
            .maximumSize(100).build();

    private static final String COIN_RATE_TICKER_CACHE = "COIN_RATE_TICKER_CACHE";

    private JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();

    @Override
    public Ticker ticker(String symbol) {
        String key = getCacheKey(symbol);
        Ticker ticker = cache.getIfPresent(key);
        if (ticker == null) {
            ticker = doTicker(symbol);
            if (ticker != null) {
                cache.put(key, ticker);
            }
        }
        return ticker;
    }

    protected Ticker doTicker(String symbol) {
        String requestUrl = coinRateProperties.getTianapi().getUrl()
                + "?key=" + coinRateProperties.getTianapi().getAccessKey() + "&coin=" + symbol;
        String resp = HttpRequest.get(requestUrl).contentType(HttpRequest.CONTENT_TYPE_JSON).body();
        TianapiResult result = jsonMapper.fromJson(resp, TianapiResult.class);
        return Collections3.getFirst(result.getNewslist());
    }

    protected String getCacheKey(String symbol) {
        return COIN_RATE_TICKER_CACHE + ":" + symbol;
    }

    @Override
    public String getName() {
        return CoinRateProvider.tianapi.code();
    }

    @PostConstruct
    public void init() {
        // 注册日期转换格式
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonMapper.getMapper().setDateFormat(smt);
        jsonMapper.getMapper().setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }
}
