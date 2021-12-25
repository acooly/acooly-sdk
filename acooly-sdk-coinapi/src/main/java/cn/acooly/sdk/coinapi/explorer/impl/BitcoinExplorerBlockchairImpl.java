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
import cn.acooly.sdk.coinapi.explorer.dto.BlockchairBitcoin;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.mapper.JsonMapper;
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
public class BitcoinExplorerBlockchairImpl extends AbstractCoinExplorer<BitcoinOverview> {


    @Override
    protected BitcoinOverview doBrowser() {
        try {
            String url = "https://api.blockchair.com/bitcoin/stats";
            String body = HttpRequest.get(url).body();
            JSONObject jsonObject = JSON.parseObject(body);
            JSONObject jsonData = (JSONObject) jsonObject.get("data");
            BlockchairBitcoin blockchairBitcoin = jsonData.toJavaObject(BlockchairBitcoin.class);
            if (blockchairBitcoin == null) {
                log.warn("原始数据解析错误，请检查数据源 url:{}, body:{}", url, body);
                throw new BusinessException(CoinApiErrors.DATA_PARSE_ERROR, "原始数据解析错误，请检查数据源。");
            }
            BitcoinOverview bit = new BitcoinOverview();
            bit.setPrice(Money.amout(blockchairBitcoin.getMarketPriceUsd()));
            bit.setHashRate(storageTo(blockchairBitcoin.getHashrate24h(), StorageUnit.E).toPlainString());
            bit.setDifficulty(new BigDecimal(blockchairBitcoin.getDifficulty()));
            bit.setExpectedNextDifficulty(storageTo(blockchairBitcoin.getNextDifficultyEstimate(), StorageUnit.T).toPlainString());
            bit.setDatetoNextDifficulty(blockchairBitcoin.getNextRetargetTimeEstimate());
            bit.setTps(Money.amout(blockchairBitcoin.getMempoolTps()).toString());
            return bit;
        } catch (Exception e) {
            log.warn("Bitcoin Overview 错误: {}", e.getMessage());
            throw new BusinessException(CoinApiErrors.DATA_PARSE_ERROR, "Ethereum网络或数据解析错误");
        }
    }

    @Override
    public DigitCurrency coin() {
        return DigitCurrency.btc;
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
