/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-26 23:02
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap.impl;

import cn.acooly.sdk.coinapi.platform.coinmarketcap.CoinmarketcapQuoteService;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.entity.CoinmarketcapQuotes;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.service.CoinmarketcapQuotesManager;
import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.Asserts;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Coinmarketcap平台报价服务
 *
 * @author zhangpu
 * @date 2021-12-26 23:02
 */
@Slf4j
@Component
public class CoinmarketcapQuoteServiceImpl implements CoinmarketcapQuoteService {

    @Autowired
    private CoinmarketcapQuotesManager coinmarketcapQuotesManager;

    @Override
    public CoinmarketcapQuotes latest(String coinCode) {
        return coinmarketcapQuotesManager.topOneByCoinCode(coinCode);
    }

    @Override
    public PageInfo<CoinmarketcapQuotes> query(PageInfo pageInfo, String coinCode, Date startTime, Date endTime) {
        return coinmarketcapQuotesManager.query(pageInfo, buildConditions(coinCode, startTime, endTime), buildSort());
    }

    @Override
    public List<CoinmarketcapQuotes> query(String coinCode, Date startTime, Date endTime) {
        return coinmarketcapQuotesManager.query(
                buildConditions(coinCode, startTime, endTime), buildSort()
        );
    }

    protected Map<String, Boolean> buildSort() {
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("id", true);
        return sortMap;
    }

    protected Map<String, Object> buildConditions(@NotBlank String coinCode, @NotBlank Date startTime, @NotBlank Date endTime) {
        Asserts.notNull(coinCode, "币种符号");
        Asserts.notNull(startTime, "更新时间开始");
        Asserts.notNull(endTime, "更新时间结束");
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_coinCode", coinCode);
        map.put("GTE_lastUpdated", startTime);
        map.put("LTE_lastUpdated", endTime);
        return map;
    }
}
