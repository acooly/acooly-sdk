/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-27 23:33
 */
package cn.acooly.sdk.exchangerate.service;

import cn.acooly.sdk.exchangerate.dto.ExchangeRates;
import cn.acooly.sdk.exchangerate.enums.LegalCurrency;
import com.acooly.core.utils.lang.Named;

import java.math.BigDecimal;

/**
 * 常用法定货币汇率查询
 *
 * @author zhangpu
 * @date 2021-05-27 23:33
 */
public interface ExchangeRateService extends Named {

    /**
     * 常用货币汇率列表
     *
     * @param currency
     * @return
     */
    ExchangeRates rates(LegalCurrency currency);

    /**
     * 获取单个汇率
     * 以from的1个标准单位进行汇率计算，返回最大四位小数值（四舍五入）
     *
     * @param from
     * @param to
     * @return
     */
    BigDecimal rate(LegalCurrency from, LegalCurrency to);
}
