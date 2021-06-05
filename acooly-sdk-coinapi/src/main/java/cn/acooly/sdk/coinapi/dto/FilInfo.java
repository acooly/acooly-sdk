/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-05 00:24
 */
package cn.acooly.sdk.coinapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangpu
 * @date 2021-06-05 00:24
 */
@Data
public class FilInfo {

    /**
     * 月份
     */
    private String month;

    /**
     * 总奖励
     */
    private BigDecimal profit;

    /**
     * 可提现
     */
    private BigDecimal withdraw;

    /**
     * 待释放
     */
    private BigDecimal lock;

    /**
     * 已质押
     */
    private BigDecimal pledge;

    /**
     * 需要质押
     */
    private BigDecimal pledgeAdd;

    /**
     * 待质押
     */
    private BigDecimal advancePledge;


    private BigDecimal releaseAndLockAdd;

}
