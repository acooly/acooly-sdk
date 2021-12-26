/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by zhangpu
 * date:2021-12-25
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.service.impl;

import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.dao.CoinmarketcapQuotesDao;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.entity.CoinmarketcapQuotes;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.service.CoinmarketcapQuotesManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;

/**
 * 币报价 Service实现
 *
 * @author zhangpu
 * @date 2021-12-25 22:22:46
 */
@Component
public class CoinmarketcapQuotesManagerImpl extends EntityServiceImpl<CoinmarketcapQuotes, CoinmarketcapQuotesDao> implements CoinmarketcapQuotesManager {

    @Override
    public CoinmarketcapQuotes topOneByCoinCode(String coinCode) {
        return getEntityDao().findTopOneByCoinCode(coinCode);
    }
}
