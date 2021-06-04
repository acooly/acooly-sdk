/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-04 16:01
 */
package cn.acooly.sdk.coinapi.service;

import cn.acooly.sdk.coinapi.CoinApiProperties;
import cn.acooly.sdk.coinapi.dto.Ticker;
import com.acooly.core.utils.mapper.JsonMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author zhangpu
 * @date 2021-06-04 16:01
 */
@Slf4j
public abstract class CoinApiServiceAbstractImpl implements CoinApiService {

    @Autowired
    protected CoinApiProperties coinApiProperties;
    protected JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
    private volatile Cache<String, Ticker> cache = null;
    private static final String COIN_API_TICKER_CACHE = "COIN_API_TICKER_CACHE";


    @Override
    public Ticker ticker(String symbol) {
        if (!coinApiProperties.getCache().isEnable()) {
            return doTicker(symbol);
        }
        Ticker ticker = getCache(symbol);
        if (ticker == null) {
            ticker = doTicker(symbol);
            if (ticker != null) {
                setCache(symbol, ticker);
            }
        }
        return ticker;
    }

    protected abstract Ticker doTicker(String symbol);

    protected Cache<String, Ticker> getCache() {
        if (this.cache == null) {
            this.cache = Caffeine.newBuilder()
                    .expireAfterWrite(this.coinApiProperties.getCache().getTimeout(),
                            TimeUnit.SECONDS)
                    .maximumSize(20).build();
        }
        return this.cache;
    }

    protected Ticker getCache(String symbol) {
        return getCache().getIfPresent(getCacheKey(symbol));
    }

    protected void setCache(String symbol, Ticker ticker) {
        getCache().put(getCacheKey(symbol), ticker);
    }

    protected String getCacheKey(String symbol) {
        return COIN_API_TICKER_CACHE + ":" + symbol;
    }

}
