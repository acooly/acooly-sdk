/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-16 17:14
 */
package cn.acooly.sdk.swft.transport;

import cn.acooly.sdk.swft.message.SwftRequest;
import cn.acooly.sdk.swft.message.SwftResponse;

/**
 * @author zhangpu
 * @date 2021-06-16 17:14
 */
public interface SwftTransport {

    /**
     * RPC请求发送
     *
     * @param request
     * @return
     */
    <T extends SwftResponse> T send(SwftRequest request, Class<T> responseClass);

}
