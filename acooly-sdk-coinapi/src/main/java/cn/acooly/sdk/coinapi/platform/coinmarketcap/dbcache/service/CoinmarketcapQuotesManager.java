/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by zhangpu
 * date:2021-12-25
 *
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.service;

import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.entity.CoinmarketcapQuotes;
import com.acooly.core.common.service.EntityService;

/**
 * 币报价 Service接口
 *
 * @author zhangpu
 * @date 2021-12-25 22:22:46
 */
public interface CoinmarketcapQuotesManager extends EntityService<CoinmarketcapQuotes> {

    /**
     * TopOne查询
     * （lastUpdatedMillis）
     *
     * @param coinCode
     * @return
     */
    CoinmarketcapQuotes topOneByCoinCode(String coinCode);

}
