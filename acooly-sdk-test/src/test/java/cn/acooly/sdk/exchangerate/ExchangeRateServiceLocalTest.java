/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-31 16:11
 */
package cn.acooly.sdk.exchangerate;

import cn.acooly.sdk.exchangerate.enums.LegalCurrency;
import cn.acooly.sdk.exchangerate.service.ExchangeRateService;
import cn.acooly.sdk.exchangerate.service.impl.ExchangeRateServiceErgImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zhangpu
 * @date 2021-05-31 16:11
 */
@Slf4j
public class ExchangeRateServiceLocalTest {

    private ExchangeRateService exchangeRateService;

    @Before
    public void before() {
        exchangeRateService = new ExchangeRateServiceErgImpl();
    }

    @Test
    public void testRate() {
        log.info("ExchangeRate [start]");
        long start = System.currentTimeMillis();
        BigDecimal rate = exchangeRateService.rate(LegalCurrency.USD, LegalCurrency.CNY);
        log.info("ExchangeRate [first] from {} to {} : {}, {}ms", LegalCurrency.USD, LegalCurrency.CNY, rate, (System.currentTimeMillis() - start));
        rate = exchangeRateService.rate(LegalCurrency.USD, LegalCurrency.CNY);
        log.info("ExchangeRate [second] from {} to {} : {}", LegalCurrency.USD, LegalCurrency.CNY, rate);
    }

}
