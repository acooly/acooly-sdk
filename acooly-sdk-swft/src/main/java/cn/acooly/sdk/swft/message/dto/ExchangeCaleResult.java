/**
 * acooly-sdk
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-18 00:04
 */
package cn.acooly.sdk.swft.message.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author zhangpu
 * @date 2021-12-18 00:04
 */
@Data
public class ExchangeCaleResult extends BaseInfo {

    /**
     * 兑换源币数量(存币数量)
     */
    private BigDecimal depositCoinAmount;

    /**
     * 兑换目标币数量（收币数量）
     */
    private BigDecimal receiveCoinAmount;

    /**
     * 闪兑手续费(收源币)
     */
    private BigDecimal depositCoinFree;


}
