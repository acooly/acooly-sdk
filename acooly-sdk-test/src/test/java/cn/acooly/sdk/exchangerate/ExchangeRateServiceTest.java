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
import cn.acooly.sdk.test.NoWebTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author zhangpu
 * @date 2021-05-31 16:11
 */
@Slf4j
public class ExchangeRateServiceTest extends NoWebTestBase {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Test
    public void testRate() {
        BigDecimal rate = exchangeRateService.rate(LegalCurrency.USD, LegalCurrency.CNY);
        log.info("ExchangeRate from {} to {} : {}", LegalCurrency.USD, LegalCurrency.CNY, rate);
    }

}
