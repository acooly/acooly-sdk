/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-26 21:35
 */
package cn.acooly.sdk.coinmarketcap;

import cn.acooly.sdk.coinapi.platform.coinmarketcap.CoinmarketcapQuoteScheduler;
import cn.acooly.sdk.test.NoWebTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangpu
 * @date 2021-12-26 21:35
 */
@Slf4j
public class CoinQuoteSchedulerSpringTest extends NoWebTestBase {

    @Autowired
    private CoinmarketcapQuoteScheduler coinmarketcapQuoteScheduler;


    @Before
    public void init() {
        // 代理参数设置
        System.setProperty("https.proxySet", "true");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "19180");
    }

    @Test
    public void testDbCache() {
        coinmarketcapQuoteScheduler.pull();
    }

}
