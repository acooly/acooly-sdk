/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2024-05-03 19:30
 */
package cn.acooly.sdk.coinapi.antpool;

import com.acooly.core.common.facade.InfoBase;
import lombok.Getter;
import lombok.Setter;

/**
 * AntPool挖矿收益计算器结果
 *
 * @author zhangpu
 * @date 2024-05-03 19:30
 */
@Getter
@Setter
public class CoinCalcResult extends InfoBase {

    /**
     * 收益模式
     */
    private String payMethod;

    /**
     * 收益币种，例如当计算SHA256币种的收益时，收益币种为BTC
     */
    private String coinType;

    /**
     * 网络难度
     */
    private String networkDiff;

    /**
     * 输入的算力值
     */
    private String hashInput;

    /**
     * 手续费率
     */
    private String feePercent;

    /**
     * 区块奖励收益（收益以币结算）
     */
    private String blockIncentiveVal;

    /**
     * 区块交易费收益（收益以币结算）
     */
    private String blockTxFee;

    /**
     * 每日收益币种的数量
     */
    private String coinMount;

    /**
     * 每日收益人民币
     */
    private String resultMountCny;

    /**
     * 每日收益美元
     */
    private String resultMountUsd;

    /**
     * 币价/人民币
     */
    private String coinPriceCny;

    /**
     * 币价/美元
     */
    private String coinPriceUsd;

}
