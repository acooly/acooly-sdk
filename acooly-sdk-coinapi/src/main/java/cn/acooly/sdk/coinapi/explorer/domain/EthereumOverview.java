/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-21 10:51
 */
package cn.acooly.sdk.coinapi.explorer.domain;

import com.acooly.core.common.facade.InfoBase;
import com.acooly.core.utils.Money;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Ethereum main-net overview
 * <p>
 * 全网有效算力（和涨跌百分比），全网难度（和涨跌百分比），单位算力收益（pps），平均出块时间
 *
 * @author zhangpu
 * @date 2021-08-21 10:51
 */
@Data
@ToString
public class EthereumOverview extends InfoBase {


    /**
     * 当前币价（$ USD）
     */
    private Money price;

    /**
     * 24小时全网有效算力（和涨跌百分比）
     * TH/s
     */
    private String hashRate;

    /**
     * 全网难度（K）（和涨跌百分比）
     */
    private BigDecimal difficulty;

    /**
     * 单位（M）算力收益（pps）：1MH/s*24H
     */
    private String earningsMpps;

    /**
     * 平均出块时间（S）
     */
    private String avgBlockTime;

    /**
     * 每秒交易量，例如：13.7 笔/s
     */
    private String tps;

    /**
     * 平均手续费（$ 16.89 /笔）
     * 美元
     */
    private String avgFee;

    /**
     * 全网难度（存储单位：P）
     *
     * @return
     */
    public String getDifficultyByPeta() {
        if (this.difficulty == null) {
            return null;
        }
        return this.difficulty.divide(BigDecimal.valueOf(1000000000000000L), 2, RoundingMode.HALF_UP).toString();
    }

}
