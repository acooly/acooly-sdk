/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-22 18:12
 */
package cn.acooly.sdk.coinapi.explorer.dto;

import com.acooly.core.common.facade.InfoBase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangpu
 * @date 2021-12-22 18:12
 */
@Data
public class BlockchairBitcoin extends InfoBase {

    /**
     * total number of outputs (including spent)
     */
    private String outputs;

    /**
     * the latest block time
     */
    private String bestBlockTime;

    /**
     * mempool size in bytes
     */
    private String mempoolSize;

    /**
     * approximate next difficulty value (this field is available for Bitcoin and Litecoin only)
     */
    private String nextDifficultyEstimate;

    /**
     * approximate timestamp of the next difficulty retarget (this field is available for Bitcoin and Litecoin only)
     */
    private String nextRetargetTimeEstimate;

    /**
     * suggests a proper transaction fee in satoshi per byte based on the latest block
     */
    private String suggestedTransactionFeePerByteSat;

    /**
     * number of transactions per second added to the mempool
     */
    private String mempoolTps;

    /**
     * average market price of 1 coin in BTC (for Bitcoin it always returns 1)
     */
    private String marketPriceBtc;

    /**
     * total size of all blocks in bytes (note: it's not the size of a full node, it's just bare blocks; nodes are bigger in size as they use database indexing, etc)
     */
    private String blockchainSize;

    /**
     * market price change in percent for 24 hours
     */
    private String marketPriceUsdChange24hPercentage;

    /**
     * market capitalization (coins in circulation * price per coin in USD)
     */
    private String marketCapUsd;

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
     * the total number of addresses with positive balance
     */
    private String hodlingAddresses;


    /**
     * sum of transaction fees in the mempool, in USD
     */
    private String mempoolTotalFeeUsd;

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
     * total monetary volume of transactions over the last 24 hours
     */
    private String volume24h;

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
     * number of full network nodes (it's an approximate number and actually not a blockchain metric)
     */
    private String nodes;

    /**
     * the same in USD
     */
    private String medianTransactionFeeUsd24h;

    /**
     * the latest block height
     */
    private String bestBlockHeight;

    /**
     * number of coins in circulation (in satoshi)
     */
    private String circulation;

    /**
     * the same in USD
     */
    private String inflationUsd24h;

    /**
     * total coindays destroyed over the last 24 hours
     */
    private String cdd24h;

    /**
     * number of outputs in the mempool
     */
    private String mempoolOutputs;


}
