/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-27 23:40
 */
package cn.acooly.sdk.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author zhangpu
 * @date 2021-05-27 23:40
 */
@Slf4j
public class CurrencyTest {

    @Test
    public void testCurrency(){
        System.out.println(Currency.getInstance(Locale.US).getCurrencyCode());

    }
}
