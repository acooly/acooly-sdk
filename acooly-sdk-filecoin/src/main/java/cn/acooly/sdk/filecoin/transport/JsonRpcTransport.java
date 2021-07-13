/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-16 17:14
 */
package cn.acooly.sdk.filecoin.transport;

import cn.acooly.sdk.filecoin.domain.JsonRpcRequest;
import cn.acooly.sdk.filecoin.domain.JsonRpcResponse;

/**
 * @author zhangpu
 * @date 2021-06-16 17:14
 */
public interface JsonRpcTransport {

    /**
     * RPC请求发送
     *
     * @param request
     * @return
     */
    <T extends JsonRpcResponse> T send(JsonRpcRequest request, Class<T> responseClass);

}
