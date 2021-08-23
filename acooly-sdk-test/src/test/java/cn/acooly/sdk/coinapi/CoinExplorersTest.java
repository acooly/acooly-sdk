/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-23 23:32
 */
package cn.acooly.sdk.coinapi;

import cn.acooly.sdk.coinapi.explorer.CoinExplorerService;
import cn.acooly.sdk.coinapi.explorer.domain.BitcoinOverview;
import cn.acooly.sdk.coinapi.explorer.domain.EthereumOverview;
import cn.acooly.sdk.coinapi.explorer.domain.FilecoinOverview;
import cn.acooly.sdk.test.NoWebTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangpu
 * @date 2021-08-23 23:32
 */
@Slf4j
public class CoinExplorersTest extends NoWebTestBase {

    @Autowired
    CoinExplorerService coinExplorerService;

    @Test
    public void testBitcoinOverview() {
        BitcoinOverview bitcoinOverview = coinExplorerService.btc();
        log.info("bitcoinOverview:{}", bitcoinOverview);
    }

    @Test
    public void testEthereumOverview() {
        EthereumOverview overview = coinExplorerService.eth();
        log.info("EthereumOverview: {}", overview);
    }

    @Test
    public void testFilecoinOverview() {
        FilecoinOverview overview = coinExplorerService.fil();
        log.info("FilecoinOverview: {}", overview);
    }

}
