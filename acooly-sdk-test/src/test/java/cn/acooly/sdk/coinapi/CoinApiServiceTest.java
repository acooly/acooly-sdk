/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-04 17:17
 */
package cn.acooly.sdk.coinapi;

import cn.acooly.sdk.coinapi.dto.Ticker;
import cn.acooly.sdk.coinapi.enums.DigitCurrency;
import cn.acooly.sdk.coinapi.service.CoinApiService;
import cn.acooly.sdk.test.NoWebTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangpu
 * @date 2021-06-04 17:17
 */
@Slf4j
public class CoinApiServiceTest extends NoWebTestBase {

    @Autowired
    private CoinApiService coinApiService;

    @Test
    public void testTicker() {
        Ticker ticker = coinApiService.ticker(DigitCurrency.fil, DigitCurrency.usdt);
        log.info("first CoinApi Ticker from {} to {} : {}", DigitCurrency.fil, DigitCurrency.usdt, ticker);
        ticker = coinApiService.ticker(DigitCurrency.fil, DigitCurrency.usdt);
        log.info("second CoinApi Ticker from {} to {} : {}", DigitCurrency.fil, DigitCurrency.usdt, ticker);
    }
}
