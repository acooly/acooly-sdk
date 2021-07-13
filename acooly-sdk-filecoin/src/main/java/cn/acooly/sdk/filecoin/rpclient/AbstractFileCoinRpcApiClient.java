/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-23 15:27
 */
package cn.acooly.sdk.filecoin.rpclient;

import cn.acooly.sdk.filecoin.transport.JsonRpcTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangpu
 * @date 2021-06-23 15:27
 */
@Slf4j
public abstract class AbstractFileCoinRpcApiClient {

    @Autowired
    protected JsonRpcTransport jsonRpcTransport;

    public AbstractFileCoinRpcApiClient() {
    }

    public AbstractFileCoinRpcApiClient(JsonRpcTransport jsonRpcTransport) {
        this.jsonRpcTransport = jsonRpcTransport;
    }
}
