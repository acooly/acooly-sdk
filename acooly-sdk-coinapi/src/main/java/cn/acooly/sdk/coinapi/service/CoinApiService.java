/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-03 14:34
 */
package cn.acooly.sdk.coinapi.service;

import cn.acooly.sdk.coinapi.dto.Ticker;
import cn.acooly.sdk.coinapi.enums.DigitCurrency;
import com.acooly.core.utils.lang.Named;
import org.springframework.core.Ordered;

/**
 * 币币汇率查询
 *
 * @author zhangpu
 * @date 2021-06-03 14:34
 */
public interface CoinApiService extends Named, Ordered {

    /**
     * 币币兑换行情查询
     *
     * @param symbol
     * @return
     */
    Ticker ticker(String symbol);

    /**
     * 币币兑换行情查询
     *
     * @param from
     * @param to
     * @return
     */
    default Ticker ticker(DigitCurrency from, DigitCurrency to) {
        String symbol = from.code() + to.code();
        return ticker(symbol);
    }
}


