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
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangpu
 * @date 2021-05-31 00:11
 */
@Slf4j
@Component
public class ExchangeRateServiceErgImpl implements ExchangeRateService {

    private static final String EXCHANGE_RESULT_CACHE_KEY = "EX_RESULT_CACHE";

    /**
     * ExchangeRates支持的货币
     */
    private static List<LegalCurrency> acceptRatesCurrency = new ArrayList<>(LegalCurrency.getAll().size());

    static {
        acceptRatesCurrency.add(LegalCurrency.USD);
        acceptRatesCurrency.add(LegalCurrency.CNY);
        acceptRatesCurrency.add(LegalCurrency.EUR);
        acceptRatesCurrency.add(LegalCurrency.GBP);
        acceptRatesCurrency.add(LegalCurrency.HKD);
        acceptRatesCurrency.add(LegalCurrency.TWD);
        acceptRatesCurrency.add(LegalCurrency.NZD);
        acceptRatesCurrency.add(LegalCurrency.JPY);
        acceptRatesCurrency.add(LegalCurrency.KRW);
        acceptRatesCurrency.add(LegalCurrency.AUD);
        acceptRatesCurrency.add(LegalCurrency.CAD);
        acceptRatesCurrency.add(LegalCurrency.TRY);
    }

    private final String defaultUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
    private final String domain = "https://cn.exchange-rates.org";
    private int cacheTimeoutSeconds = 2 * 60;
    Cache<String, Object> cache = Caffeine.newBuilder()
            .expireAfterWrite(this.cacheTimeoutSeconds, TimeUnit.SECONDS)
            .maximumSize(100).build();

    public static void main(String[] args) {
        ExchangeRateServiceErgImpl ers = new ExchangeRateServiceErgImpl();
        BigDecimal money = ers.rate(LegalCurrency.USD, LegalCurrency.CNY);
        System.out.println(money);
        money = ers.rate(LegalCurrency.USD, LegalCurrency.EUR);
        System.out.println(money);
    }

    /**
     * 常用货币汇率列表
     *
     * @param currency
     * @return
     */
    @Override
    public ExchangeRates rates(LegalCurrency currency) {
        String key = getRatesCacheKey(currency);
        ExchangeRates money = (ExchangeRates) cache.getIfPresent(key);
        if (money == null) {
            money = doExchangeRates(currency);
            if (money != null) {
                cache.put(key, money);
            }
        }
        return money;
    }


    public ExchangeRates doExchangeRates(LegalCurrency currency) {

        if (!acceptRatesCurrency.contains(currency)) {
            throw new BusinessException(CommonErrorCodes.UNSUPPORTED_ERROR);
        }

        ExchangeRates exRates = new ExchangeRates();
        exRates.setAmount(1);
        exRates.setCurrency(currency);
        exRates.setProvider(this.getName());
        exRates.setRates(new HashMap<>());
        exRates.getRates().put(currency, new BigDecimal("1"));

        try {

            String[] regionArray = {"A", "P", "E", "M", "F"};
            outLoop:
            for (String region : regionArray) {

                String url = domain + "/currentRates/" + region + "/" + currency.getCode();
                Document doc = Jsoup.connect(url)
                        .userAgent(defaultUserAgent)
                        .get();
                Element resultEle = doc.selectFirst(".table-fixedX");
                if (resultEle == null) {
                    throw new BusinessException(CommonErrorCodes.INTERNAL_ERROR, "网站已改版，需要更新组件！");
                }

                Elements select = resultEle.select("tbody > tr");

                for (Element elementTR : select) {
                    String code = elementTR.selectFirst("span.code").text();
                    //系统不支持的
                    LegalCurrency legalCurrency = LegalCurrency.find(code);
                    if (legalCurrency == null) {
                        continue;
                    }

                    String toAmount = elementTR.selectFirst("td.text-rate").text();
                    BigDecimal amount = new BigDecimal(toAmount);
                    amount.setScale(5, BigDecimal.ROUND_HALF_UP);
                    exRates.getRates().put(legalCurrency, amount);

                    if (exRates.getRates().size() == acceptRatesCurrency.size()) {
                        break outLoop;
                    }
                }
            }
            return exRates;
        } catch (Exception e) {
            log.warn("汇率获取 失败 currency:{}, Error:{}", currency, e.getMessage());
        }
        return null;


    }

    @Override
    public BigDecimal rate(LegalCurrency from, LegalCurrency to) {
        String key = getRateCacheKey(from, to);
        BigDecimal money = (BigDecimal) cache.getIfPresent(key);
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

    protected String getRatesCacheKey(LegalCurrency from) {
        return EXCHANGE_RESULT_CACHE_KEY + ":" + from.code();
    }

    protected BigDecimal doExchange(LegalCurrency from, LegalCurrency to) {
        try {
            String url = domain + "/converter/" + from.code() + "/" + to.code() + "/1";
            Document doc = Jsoup.connect(url)
                    .userAgent(defaultUserAgent)
                    .get();
            Element resultEle = doc.selectFirst(".result-cur2");
            if (resultEle != null) {
                // 去除科学计数法的逗号
                String toAmount = resultEle.selectFirst("span").text().replaceAll(",", "");
                BigDecimal amount = new BigDecimal(toAmount);
                amount.setScale(4, BigDecimal.ROUND_HALF_UP);
                return amount;
            }
        } catch (Exception e) {
            log.warn("汇率转换 失败 from:{}, to:{}, Error:{}", from, to, e.getMessage());
        }
        return null;
    }

    @Override
    public String getName() {
        return ExchangeRateProvider.ExchangeRatesOrg.code();
    }
}
