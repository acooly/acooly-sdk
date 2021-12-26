/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by zhangpu
 * date:2021-12-25
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.dao;

import com.acooly.module.mybatis.EntityMybatisDao;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.entity.CoinmarketcapQuotes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 币报价 Mybatis Dao
 *
 * @author zhangpu
 * @date 2021-12-25 22:22:46
 */
public interface CoinmarketcapQuotesDao extends EntityMybatisDao<CoinmarketcapQuotes> {

    /**
     * Top 1
     *
     * @param coinCode
     * @return
     */
    @Select("SELECT *  FROM `acooly-sdk`.coinapi_coinmarketcap_quotes where coin_code = #{coinCode} order by last_updated_millis desc  limit 1;")
    CoinmarketcapQuotes findTopOneByCoinCode(@Param("coinCode") String coinCode);

}
