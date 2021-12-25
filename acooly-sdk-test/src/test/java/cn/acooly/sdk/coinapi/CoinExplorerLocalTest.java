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
import cn.acooly.sdk.coinapi.explorer.impl.*;
import cn.acooly.sdk.coinapi.fil.FileCoinNetworkInfo;
import cn.acooly.sdk.coinapi.fil.impl.FilFoxFileCoinNetworkService;
import cn.acooly.sdk.coinapi.quote.BinanceQuoteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2021-08-23 10:28
 */
@Slf4j
public class CoinExplorerLocalTest {

    CoinApiProperties coinApiProperties = new CoinApiProperties();
    ExplorerCacheManager explorerCacheManager = new ExplorerCacheManager(coinApiProperties);
    BinanceQuoteService binanceQuoteService = new BinanceQuoteService(coinApiProperties);

    private EthereumExplorerBlockchairImpl ethereumExplorerBlockchair = new EthereumExplorerBlockchairImpl();
    private EthereumExplorerBtcImpl ethExplorerBtc = new EthereumExplorerBtcImpl();
    private BitcoinExplorerBtcImpl bitcoinExplorerBtc = new BitcoinExplorerBtcImpl();
    private BitcoinExplorerBlockchairImpl bitcoinExplorerBlockchair = new BitcoinExplorerBlockchairImpl();
    private FilecoinExplorerFilfoxImpl filecoinExplorerFilfox = new FilecoinExplorerFilfoxImpl();

    String PROXY_HOST = "127.0.0.1";
    int PROXY_PORT = 19180;

    @Before
    public void init() {

        System.setProperty("https.proxySet", "true");
        System.setProperty("https.proxyHost", PROXY_HOST);
        System.setProperty("https.proxyPort", String.valueOf(PROXY_PORT));


        ethereumExplorerBlockchair.setExplorerCacheManager(explorerCacheManager);
        ethExplorerBtc.setExplorerCacheManager(explorerCacheManager);

        bitcoinExplorerBtc.setExplorerCacheManager(explorerCacheManager);
        bitcoinExplorerBlockchair.setExplorerCacheManager(explorerCacheManager);

        filecoinExplorerFilfox.setExplorerCacheManager(explorerCacheManager);
        filecoinExplorerFilfox.setBinanceQuoteService(binanceQuoteService);
    }


    @Test
    public void testBitcoinOverview() {
        BitcoinOverview bitcoinOverview = bitcoinExplorerBtc.browse();
        log.info("BitcoinOverview: {},{}", bitcoinOverview, bitcoinOverview.getDifficultyByTera());
    }

    @Test
    public void testBitcoinExplorerBlockchair() {
        BitcoinOverview bitcoinOverview = bitcoinExplorerBlockchair.browse();
        log.info("BitcoinOverview: {},{}", bitcoinOverview, bitcoinOverview.getDifficultyByTera());
    }

    @Test
    public void testEthExplorerBtcImpl() {
        EthereumOverview ethNetOverview = null;
        ethNetOverview = ethExplorerBtc.browse();
        log.info("EthereumOverview: {},{}", ethNetOverview, ethNetOverview.getDifficultyByPeta());
    }

    @Test
    public void testEthereumExplorerBlockchair() {
        EthereumOverview ethNetOverview = ethereumExplorerBlockchair.browse();
        log.info("EthereumOverview: {},{}", ethNetOverview, ethNetOverview.getDifficultyByPeta());
    }


    @Test
    public void testNewFilecoinOverview() {
        FilecoinOverview filecoinOverview = null;
        filecoinOverview = filecoinExplorerFilfox.browse();
        // 二次调用，测试缓存（看日志时间）
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
