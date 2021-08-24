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
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BTC main-net overview
 * 全网有效算力（和涨跌百分比），全网难度（和涨跌百分比），出块奖励，24h平均挖矿收益，平均出块时间（？）
 *
 * @author zhangpu
 * @date 2021-08-21 10:51
 */
@Data
@ToString
public class BitcoinOverview extends InfoBase {

    /**
     * 全网算力（EH/s）
     */
    private String hashRate;

    /**
     * 全网难度
     */
    private BigDecimal difficulty;

    /**
     * 预测下次难度涨幅比例
     */
    private String expectedNextDifficulty;

    /**
     * 距离下次难度时间
     */
    private String datetoNextDifficulty;

    /**
     * 24h平均挖矿收益/每T收益（PPS）：1T*24H
     */
    private String earningsTpps;

    /**
     * 24h平均挖矿收益/每T收益（FPPS）：1T*24H
     */
    private String earningsTfpps;

    /**
     * 平均出块时间（min 分钟）
     */
//    private String averageBlockInterval;


    /**
     * 全网难度（存储单位：T）
     *
     * @return
     */
    public String getDifficultyByTera() {
        if (this.difficulty == null) {
            return null;
        }
        return this.difficulty.divide(BigDecimal.valueOf(1000000000000L), 2, RoundingMode.HALF_UP).toString();
    }
}
