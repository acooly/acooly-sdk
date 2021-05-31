/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-31 00:11
 */
package cn.acooly.sdk.exchangerate.service.impl;

import cn.acooly.sdk.exchangerate.dto.ExchangeRates;
import cn.acooly.sdk.exchangerate.enums.ExchangeRateProvider;
import cn.acooly.sdk.exchangerate.enums.LegalCurrency;
import cn.acooly.sdk.exchangerate.service.ExchangeRateService;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangpu
 * @date 2021-05-31 00:11
 */
@Slf4j
@Component
public class ExchangeRateServiceErgImpl implements ExchangeRateService {

    private int cacheTimeoutSeconds = 2 * 60;
    Cache<String, BigDecimal> cache = Caffeine.newBuilder()
            .expireAfterWrite(this.cacheTimeoutSeconds, TimeUnit.SECONDS)
            .maximumSize(100).build();

    private static final String EXCHANGE_RESULT_CACHE_KEY = "EX_RESULT_CACHE";

    @Override
    public ExchangeRates rates(LegalCurrency currency) {
        throw new BusinessException(CommonErrorCodes.UNSUPPORTED_ERROR);
    }

    @Override
    public BigDecimal rate(LegalCurrency from, LegalCurrency to) {
        String key = getRateCacheKey(from, to);
        BigDecimal money = cache.getIfPresent(key);
        if (money == null) {
            money = doExchange(from, to);
            if (money != null) {
                cache.put(key, money);
            }
        }
        return money;
    }

    protected String getRateCacheKey(LegalCurrency from, LegalCurrency to) {
        return EXCHANGE_RESULT_CACHE_KEY + ":" + from.code() + ":" + to.code();
    }


    protected BigDecimal doExchange(LegalCurrency from, LegalCurrency to) {
        try {
            String url = "https://cn.exchange-rates.org/converter/" + from.code() + "/" + to.code() + "/1";
            String resp = HttpRequest.get(url).body();
            Document doc = Jsoup.parse(resp);
            Elements elements = doc.select("#ctl00_M_lblToAmount");
            if (elements != null && elements.size() > 0) {
                String toAmount = elements.first().text();
                BigDecimal amount = new BigDecimal(toAmount);
                amount.setScale(4, BigDecimal.ROUND_HALF_UP);
                return amount;
            }
        } catch (Exception e) {
            log.warn("汇率转换 失败 from:{}, to:{}, Error:{}", from, to, e.getMessage());
        }
        return null;
    }


    public static void main(String[] args) {
        ExchangeRateServiceErgImpl ers = new ExchangeRateServiceErgImpl();
        BigDecimal money = ers.rate(LegalCurrency.USD, LegalCurrency.CNY);
        System.out.println(money);
        money = ers.rate(LegalCurrency.USD, LegalCurrency.CNY);
        System.out.println(money);
    }

    @Override
    public String getName() {
        return ExchangeRateProvider.ExchangeRatesOrg.code();
    }
}
