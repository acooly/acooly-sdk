/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-05 23:55
 */
package cn.acooly.sdk.coinapi.fil;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author zhangpu
 * @date 2021-06-05 23:55
 */
@Data
@ToString
public class FileCoinNetworkInfo {

    /**
     * 区块高度
     */
    private int blockHeight;

    /**
     * 全网有效算力（P）
     */
    private BigDecimal networkStoragePower;

    /**
     * 活跃旷工数
     */
    private int activeMiners;

    /**
     * 每区块奖励（FIL）
     * 当前高度下的区块奖励，每个高度有多个区块，每个区块均可获得该奖励
     */
    private BigDecimal blockReward;

    /**
     * 24h平均挖矿收益(FIL/TiB)
     * 近24h每个高度出块奖励与有效算力比值的均值
     */
    private BigDecimal periodAverageMiningReward;

    /**
     * 近24h产出量(FIL)
     * 近24h累计挖矿产出的FIL数量
     */
    private BigDecimal periodFILProduction;

    /**
     * 当前扇区质押量
     * 单位：FIL/32GiB
     */
    private BigDecimal currentSectorInitialPledge;

    /**
     * FIL质押量(FIL)
     * 当前矿工进行挖矿所质押的FIL总和
     */
    private BigDecimal totalPledgeCollateral;

    /**
     * 24h消息数
     */
    private int periodMessages;

    /**
     * FIL流通量(FIL)
     * 当前自由流通的FIL总和
     */
    private int circulatingSupply;

    /**
     * 总账户数
     */
    private int totalAccounts;

    /**
     * 平均区块间隔(秒)
     * 近24h出块的平均时间间隔
     */
    private BigDecimal averageBlockInterval;

    /**
     * 平均每高度区块数量
     * 近24h相同高度下的平均区块数量
     */
    private BigDecimal averageBlocksperTipset;


    /**
     * 新增算力成本（FIL/TiB）
     * 新增算力所需要花费的成本，包括扇区质押和封装手续费
     */
    private BigDecimal costofSealingSectors;

    /**
     * 当前基础费率(nanoFIL)
     */
    private BigDecimal currentBaseFee;

    /**
     * FIL销毁量(FIL)
     */
    private BigDecimal burntFIL;

    /**
     * FIL总供给量(FIL)
     */
    private int totalMaxSupply;

    /**
     * FIL流通率(%)
     * 流通率=流通量/总供给量
     */
    private BigDecimal circulatingRate;

    /**
     * 当前价格($)
     */
    private BigDecimal currentPrice;

}
