/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by zhangpu
 * date:2021-12-25
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.web;

import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.entity.CoinmarketcapQuotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJsonEntityController;
import cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.service.CoinmarketcapQuotesManager;

/**
 * 币报价 管理控制器
 *
 * @author zhangpu
 * @date 2021-12-25 22:22:46
 */
@Controller
@RequestMapping(value = "/manage/sdk/coinapi/coinmarketcapQuotes")
public class CoinmarketcapQuotesManagerController extends AbstractJsonEntityController<CoinmarketcapQuotes, CoinmarketcapQuotesManager> {


    {
        allowMapping = "*";
    }

    @SuppressWarnings("unused")
    @Autowired
    private CoinmarketcapQuotesManager coinmarketcapQuotesManager;


}
