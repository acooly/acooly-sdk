/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-23 15:30
 */
package cn.acooly.sdk.filecoin;

import cn.acooly.sdk.filecoin.transport.JsonRpcHttpTransport;
import cn.acooly.sdk.filecoin.rpclient.FileCoinChain;
import cn.acooly.sdk.filecoin.rpclient.message.ChainGetBlockMessagesRpcResponse;
import cn.acooly.sdk.filecoin.rpclient.message.ChainGetTipSetByHeightRpcResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2021-06-23 15:30
 */
@Slf4j
public class FileCoinChainTest extends FileCoinRpcBaseTest {

    private static FileCoinChain fileCoinChain;

    @BeforeClass
    public static void init() {
        FilecoinSdkProperties filecoinSdkProperties = new FilecoinSdkProperties();
        filecoinSdkProperties.getGateway().setToken(GATEWAY_TOKEN);
        filecoinSdkProperties.getGateway().setUrl(GATEWAY_URL);
        transport = new JsonRpcHttpTransport(filecoinSdkProperties);
        fileCoinChain = new FileCoinChain(transport);
        log.info("完成初始化: {}", filecoinSdkProperties.getGateway());
    }


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
        String cid = "bafy2bzacedl6fzadocccxqxdi63u3vr74jcawk3m6qatpxwpzhttmlff6cnw6";
        ChainGetBlockMessagesRpcResponse.ChainGetBlockMessages chainGetBlockMessages
                = fileCoinChain.chainGetBlockMessages(cid);
        log.info("ChainGetBlockMessages cid:{}, result: {}", cid, chainGetBlockMessages);
    }

}
