/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-23 15:30
 */
package cn.acooly.sdk.filecoin;

import cn.acooly.sdk.filecoin.rpclient.FileCoinChain;
import cn.acooly.sdk.filecoin.rpclient.message.ChainGetBlockMessagesRpcResponse;
import cn.acooly.sdk.filecoin.rpclient.message.ChainGetTipSetByHeightRpcResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2021-06-23 15:30
 */
@Slf4j
public class FileCoinChainTest extends FileCoinRpcBaseTest {

    private static FileCoinChain fileCoinChain = new FileCoinChain(transport);

    /**
     * 测试: 获取区块高度
     */
    @Test
    public void testGetBlockHeight() {
        Integer height = fileCoinChain.getBlockHeight();
        log.info("Block Height: {}", height);
    }

    @Test
    public void testChainGetTipSetByHeight() {
        Integer blockHeight = 29191;
        ChainGetTipSetByHeightRpcResponse.ChainGetTipSetByHeight chainGetTipSetByHeight
                = fileCoinChain.chainGetTipSetByHeight(blockHeight);
        log.info("ChainGetTipSetByHeight: blockHeight:{}, result: {}", blockHeight, chainGetTipSetByHeight);
    }


    @Test
    public void testChainGetBlockMessages() {
        String cid = "bafy2bzaced6jjnovcrjjd7hwdr2ywr7nkr4o2ui3oivahib57bdrya3ge2og2";
        ChainGetBlockMessagesRpcResponse.ChainGetBlockMessages chainGetBlockMessages
                = fileCoinChain.chainGetBlockMessages(cid);
        log.info("ChainGetBlockMessages cid:{}, result: {}", cid, chainGetBlockMessages);
    }

}
