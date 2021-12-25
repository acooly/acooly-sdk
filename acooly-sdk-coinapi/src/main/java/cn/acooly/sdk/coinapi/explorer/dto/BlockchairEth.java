/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-23 21:17
 */
package cn.acooly.sdk.coinapi.explorer.dto;

import com.acooly.core.common.facade.InfoBase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangpu
 * @date 2021-12-23 21:17
 */
@Data
public class BlockchairEth extends InfoBase {

    /**
     * the latest block time
     */
    private String bestBlockTime;

    /**
     * total monetary volume of transactions over the last 24 hours
     */
    private String volume24hApproximate;

    /**
     * biggest payment over the last 24 hours
     */
    private String largestTransaction24h;

    /**
     * median simple transfer fee over the last 24 hours
     */
    private String medianSimpleTransactionFee24h;

    /**
     * median gas price of transactions in the mempool
     */
    private String mempoolMedianGasPrice;

    /**
     * number of coins in circulation (in wei)
     */
    private String circulationApproximate;

    /**
     * number of transactions per second added to the mempool
     */
    private String mempoolTps;

    /**
     * average market price of 1 coin in BTC
     */
    private String marketPriceBtc;

    /**
     * total size of all blocks in bytes (note: it's not the size of a full node, it's just bare blocks; nodes are bigger in size as they use database indexing, etc)
     */
    private String blockchainSize;

    /**
     * total number of uncles
     */
    private String uncles;

    /**
     * market price change in percent for 24 hours
     */
    private String marketPriceUsdChange24hPercentage;

    /**
     * market capitalization (coins in circulation * price per coin in USD)
     */
    private String marketCapUsd;

    /**
     * number of uncles over the last 24 hours
     */
    private String uncles24h;

    /**
     * the latest block hash
     */
    private String bestBlockHash;

    /**
     * average transaction fee over the last 24 hours
     */
    private String averageTransactionFee24h;

    /**
     * number of new coins mined over the last 24 hours (in satoshi), this can be considered as the daily inflation
     */
    private String inflation24h;

    /**
     * the same in USD
     */
    private String averageSimpleTransactionFeeUsd24h;

    /**
     * the same in USD
     */
    private String medianSimpleTransactionFeeUsd24h;

    /**
     * the same in USD
     */
    private String averageTransactionFeeUsd24h;

    /**
     * number of transactions confirmed over the last 24 hours
     */
    private String transactions24h;

    /**
     * total number of blocks (note that it's 1 more than the latest block number as there is block #0)
     */
    private String blocks;

    /**
     * approximated hashrate over the last 24 hours (returned as a string as it doesn't fit into an integer)
     */
    private String hashrate24h;

    /**
     * total number of transactions
     */
    private String transactions;

    /**
     * median transaction fee over the last 24 hours
     */
    private String medianTransactionFee24h;

    /**
     * number of blocks mined over the last 24 hours
     */
    private String blocks24h;

    /**
     * average market price of 1 coin in USD (market data source: CoinGecko)
     */
    private String marketPriceUsd;

    /**
     * current mining difficulty
     */
    private String difficulty;

    /**
     * number of transactions in the mempool
     */
    private String mempoolTransactions;

    /**
     * dominance index (how much % of the total cryptocurrency market is the market capitalization of the coin)
     */
    private String marketDominancePercentage;

    /**
     * the same in USD
     */
    private String medianTransactionFeeUsd24h;

    /**
     * the latest block height
     */
    private String bestBlockHeight;

    /**
     * total number of internal calls
     */
    private String calls;

    /**
     * average simple transfer (i.e. just sending ethers for 21.000 gas) fee over the last 24 hours
     */
    private String averageSimpleTransactionFee24h;

    /**
     * the same in USD
     */
    private String inflationUsd24h;

    /**
     * sum of transaction amounts in the mempool, in wei
     */
    private String mempoolTotalValueApproximate;

}
