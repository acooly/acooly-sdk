/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-27 23:48
 */
package cn.acooly.sdk.exchangerate.dto;

import cn.acooly.sdk.exchangerate.enums.LegalCurrency;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-05-27 23:48
 */
@Getter
@Setter
public class ExchangeRates {

    private String provider;
    private Date lastUpdateTime;
    private LegalCurrency currency;
    private int amount;
    /**
     * 常用货币汇率
     */
    private Map<LegalCurrency, BigDecimal> rates = Maps.newHashMap();

}
