/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-23 10:28
 */
package cn.acooly.sdk.coinapi;

import cn.acooly.sdk.coinapi.explorer.ExplorerCacheManager;
import cn.acooly.sdk.coinapi.explorer.domain.BitcoinOverview;
import cn.acooly.sdk.coinapi.explorer.domain.EthereumOverview;
import cn.acooly.sdk.coinapi.explorer.domain.FilecoinOverview;
import cn.acooly.sdk.coinapi.explorer.impl.BitcoinExplorerBtcImpl;
import cn.acooly.sdk.coinapi.explorer.impl.EthereumExplorerBtcImpl;
import cn.acooly.sdk.coinapi.explorer.impl.FilecoinExplorerFilfoxImpl;
import cn.acooly.sdk.coinapi.fil.FileCoinNetworkInfo;
import cn.acooly.sdk.coinapi.fil.impl.FilFoxFileCoinNetworkService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2021-08-23 10:28
 */
@Slf4j
public class CoinExplorerTest {


    private EthereumExplorerBtcImpl ethExplorerBtc = new EthereumExplorerBtcImpl();
    private BitcoinExplorerBtcImpl bitcoinExplorerBtc = new BitcoinExplorerBtcImpl();
    private FilecoinExplorerFilfoxImpl filecoinExplorerFilfox = new FilecoinExplorerFilfoxImpl();

    @Before
    public void init() {
        ExplorerCacheManager explorerCacheManager = new ExplorerCacheManager();
        ethExplorerBtc.setExplorerCacheManager(explorerCacheManager);
        bitcoinExplorerBtc.setExplorerCacheManager(explorerCacheManager);
        filecoinExplorerFilfox.setExplorerCacheManager(explorerCacheManager);
    }

    @Test
    public void testEthExplorerBtcImpl() {
        EthereumOverview ethNetOverview = null;
        ethNetOverview = ethExplorerBtc.browse();
        ethNetOverview = ethExplorerBtc.browse();
        log.info("EthereumOverview: {},{}", ethNetOverview, ethNetOverview.getDifficultyByPeta());

        BitcoinOverview bitcoinOverview = null;
        bitcoinOverview = bitcoinExplorerBtc.browse();
        bitcoinOverview = bitcoinExplorerBtc.browse();
        log.info("BitcoinOverview: {},{}", bitcoinOverview, bitcoinOverview.getDifficultyByTera());
    }


    @Test
    public void testNewFilecoinOverview() {
        FilecoinOverview filecoinOverview = null;
        filecoinOverview = filecoinExplorerFilfox.browse();
        filecoinOverview = filecoinExplorerFilfox.browse();
        log.info("FilecoinOverview: {},{}", filecoinOverview);
    }

    @Test
    public void testFilecoinOverview() {
        FilFoxFileCoinNetworkService filFoxFileCoinNetworkService = new FilFoxFileCoinNetworkService();
        FileCoinNetworkInfo fileCoinNetworkInfo = filFoxFileCoinNetworkService.overview();
        System.out.println(fileCoinNetworkInfo);
    }

}
