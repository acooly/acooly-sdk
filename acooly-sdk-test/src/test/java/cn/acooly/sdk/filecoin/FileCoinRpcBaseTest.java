/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-23 14:06
 */
package cn.acooly.sdk.filecoin;

import cn.acooly.sdk.filecoin.transport.JsonRpcHttpTransport;
import cn.acooly.sdk.filecoin.transport.JsonRpcTransport;
import cn.acooly.sdk.filecoin.domain.JsonRpcRequest;
import cn.acooly.sdk.filecoin.domain.JsonRpcResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2021-06-23 14:06
 */
@Slf4j
public class FileCoinRpcBaseTest {

    protected static final String GATEWAY_URL = "http://47.57.4.57:1234/rpc/v0";
    protected static final String GATEWAY_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
            ".eyJBbGxvdyI6WyJyZWFkIiwid3JpdGUiLCJzaWduIiwiYWRtaW4iXX0" +
            ".JVi0UQGjILueyZzH0tv_eSN_hjW0HqLOvlR40MC8esQ";

    protected static FilecoinSdkProperties filecoinSdkProperties = new FilecoinSdkProperties(GATEWAY_URL, GATEWAY_TOKEN);
    protected static JsonRpcTransport transport = new JsonRpcHttpTransport(filecoinSdkProperties);


    /**
     * 测试查询网络名称
     * Filecoin.StateNetworkName
     */
    @Test
    public void testFileCoin_StateNetworkName() {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod("Filecoin.StateNetworkName");
        request.setId(Long.valueOf(RandomStringUtils.randomNumeric(4)));
        JsonRpcResponse response = transport.send(request, JsonRpcResponse.class);
        log.info("response: {}", response);
    }


}
