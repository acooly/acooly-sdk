/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-09 16:21
 */
package cn.acooly.sdk.filecoin;

import cn.acooly.sdk.filecoin.domain.FilMessage;
import cn.acooly.sdk.filecoin.rpclient.FileCoinGas;
import cn.acooly.sdk.filecoin.transport.JsonRpcHttpTransport;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigInteger;

/**
 * @author zhangpu
 * @date 2021-07-09 16:21
 */
@Slf4j
public class FileCoinGasTest extends FileCoinRpcBaseTest {

    private static FileCoinGas fileCoinGas;


    @Test
    public void testGasEstimateFeeCap() {
        FilMessage message = new FilMessage("t1vwj5ia2ge2h2fydqage3k2csibirdpz2k7bbkfi",
                "t1lpsc533zpoc2qcubugx5the25nnwfsffx3e6njq", "10");
        String gasFeeCap = fileCoinGas.gasEstimateFeeCap(message);
        log.info("FilMessage GasEstimateFeeCap: {}", gasFeeCap);
    }


    @Test
    public void testGasEstimateGasLimit() {
        FilMessage message = new FilMessage("t1vwj5ia2ge2h2fydqage3k2csibirdpz2k7bbkfi",
                "t1lpsc533zpoc2qcubugx5the25nnwfsffx3e6njq", "10");
        BigInteger gasLimit = fileCoinGas.gasEstimateGasLimit(message);
        log.info("FilMessage GasEstimateGasLimit: {}", gasLimit);
    }


    @BeforeClass
    public static void init() {
        FilecoinSdkProperties filecoinSdkProperties = new FilecoinSdkProperties();
        filecoinSdkProperties.getGateway().setToken(GATEWAY_TOKEN);
        filecoinSdkProperties.getGateway().setUrl(GATEWAY_URL);
        transport = new JsonRpcHttpTransport(filecoinSdkProperties);
        fileCoinGas = new FileCoinGas(transport);
        log.info("完成初始化: {}", filecoinSdkProperties.getGateway());
    }
}
