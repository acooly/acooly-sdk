/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-23 10:17
 */
package cn.acooly.sdk.coinapi.explorer.impl;

import cn.acooly.sdk.coinapi.enums.CoinApiErrors;
import cn.acooly.sdk.coinapi.enums.DigitCurrency;
import cn.acooly.sdk.coinapi.explorer.AbstractCoinExplorer;
import cn.acooly.sdk.coinapi.explorer.domain.BitcoinOverview;
import cn.acooly.sdk.coinapi.explorer.domain.EthereumOverview;
import cn.acooly.sdk.coinapi.explorer.dto.BlockchairBitcoin;
import cn.acooly.sdk.coinapi.explorer.dto.BlockchairEth;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Money;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BTC浏览器 from blockchair
 *
 * @author zhangpu
 * @date 2021-08-23 10:17
 */
@Slf4j
@Component
public class EthereumExplorerBlockchairImpl extends AbstractCoinExplorer<EthereumOverview> {


    @Override
    protected EthereumOverview doBrowser() {
        try {
            String url = "https://api.blockchair.com/ethereum/stats";
            String body = HttpRequest.get(url).body();
            JSONObject jsonObject = JSON.parseObject(body);
            JSONObject jsonData = (JSONObject) jsonObject.get("data");
            BlockchairEth blockchairEth = jsonData.toJavaObject(BlockchairEth.class);
            if (blockchairEth == null) {
                log.warn("原始数据解析错误，请检查数据源 url:{}, body:{}", url, body);
                throw new BusinessException(CoinApiErrors.DATA_PARSE_ERROR, "原始数据解析错误，请检查数据源。");
            }
            EthereumOverview eth = new EthereumOverview();
            eth.setPrice(Money.amout(blockchairEth.getMarketPriceUsd()));
            eth.setHashRate(storageTo(blockchairEth.getHashrate24h(), StorageUnit.T).toPlainString());
            eth.setDifficulty(new BigDecimal(blockchairEth.getDifficulty()));
            // 平均出块时间计算
            Money avgBlockTime = Money.yuan(24 * 3600).divide(Long.valueOf(blockchairEth.getBlocks24h()));
            eth.setAvgBlockTime(avgBlockTime.toString());
            // 平均交易费用
            eth.setAvgFee(Money.amout(blockchairEth.getAverageTransactionFeeUsd24h()).toString());
            // TPS计算
            Money transactions24h = Money.amout(blockchairEth.getTransactions24h());
            eth.setTps(transactions24h.divide(24 * 3600).toString());
            return eth;
        } catch (Exception e) {
            log.warn("Ethereum Overview 错误: {}", e.getMessage());
            throw new BusinessException(CoinApiErrors.DATA_PARSE_ERROR, "Ethereum网络或数据解析错误");
        }
    }

    @Override
    public DigitCurrency coin() {
        return DigitCurrency.eth;
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }


}
