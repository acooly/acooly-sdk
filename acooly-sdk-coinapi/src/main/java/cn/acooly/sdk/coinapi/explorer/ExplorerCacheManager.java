/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-23 13:23
 */
package cn.acooly.sdk.coinapi.explorer;

import cn.acooly.sdk.coinapi.CoinApiProperties;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangpu
 * @date 2021-08-23 13:23
 */
@Slf4j
@Component
public class ExplorerCacheManager {

    @Autowired(required = false)
    private CoinApiProperties coinApiProperties;

    private volatile Cache<String, Object> cache = null;

    private static final String COIN_EXPLORER_CACHE = "COIN_EXPLORER_CACHE";

    protected Cache<String, Object> getCache() {
        long expireSeconds = this.coinApiProperties != null ? this.coinApiProperties.getExplorer().getCache().getTimeout() : 2 * 60;
        long maximumSize = this.coinApiProperties != null ? this.coinApiProperties.getExplorer().getCache().getSize() : 20;
        if (this.cache == null) {
            this.cache = Caffeine.newBuilder()
                    .expireAfterWrite(expireSeconds, TimeUnit.SECONDS)
                    .maximumSize(maximumSize).build();
        }
        return this.cache;
    }

    public Object getCache(String symbol) {
        return getCache().getIfPresent(getCacheKey(symbol));
    }

    public void setCache(String symbol, Object object) {
        getCache().put(getCacheKey(symbol), object);
    }

    public String getCacheKey(String symbol) {
        return COIN_EXPLORER_CACHE + ":" + symbol;
    }


}
