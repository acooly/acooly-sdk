/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-10 11:56
 */
package cn.acooly.sdk.filecoin;

import cn.acooly.sdk.filecoin.rpclient.FileCoinMpool;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2021-07-10 11:56
 */
@Slf4j
public class FileCoinMpoolTest extends FileCoinRpcBaseTest {

    FileCoinMpool fileCoinMpool = new FileCoinMpool(transport);

    String from = "t1vwj5ia2ge2h2fydqage3k2csibirdpz2k7bbkfi";

    @Test
    public void testMpoolGetNonce() {
        Integer nonce = fileCoinMpool.mpoolGetNonce(from);
        log.info("latest nonce: {}", nonce);
    }


}
