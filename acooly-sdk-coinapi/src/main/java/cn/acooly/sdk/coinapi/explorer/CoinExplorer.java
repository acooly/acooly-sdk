/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-21 09:52
 */
package cn.acooly.sdk.coinapi.explorer;

import cn.acooly.sdk.coinapi.enums.DigitCurrency;

/**
 * @author zhangpu
 * @date 2021-08-21 09:52
 */
public interface CoinExplorer<T> {

    /**
     * 全网数据
     *
     * @return
     */
    T browse();


    /**
     * 币种
     *
     * @return
     */
    DigitCurrency coin();

}
