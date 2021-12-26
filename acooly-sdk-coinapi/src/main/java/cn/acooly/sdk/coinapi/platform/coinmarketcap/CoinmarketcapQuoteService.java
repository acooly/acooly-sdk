/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-25 22:25
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap;

import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.entity.CoinmarketcapQuotes;
import com.acooly.core.common.dao.support.PageInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数字货币报价服务
 *
 * @author zhangpu
 * @date 2021-12-25 22:25
 */
public interface CoinmarketcapQuoteService {

    /**
     * 最新报价(美元)
     *
     * @param coinCode
     * @return
     */
    CoinmarketcapQuotes latest(String coinCode);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @param coinCode
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<CoinmarketcapQuotes> query(PageInfo pageInfo, String coinCode, Date startTime, Date endTime);

    /**
     * 条件查询
     *
     * @param coinCode
     * @param startTime
     * @param endTime
     * @return
     */
    List<CoinmarketcapQuotes> query(String coinCode, Date startTime, Date endTime);

}
