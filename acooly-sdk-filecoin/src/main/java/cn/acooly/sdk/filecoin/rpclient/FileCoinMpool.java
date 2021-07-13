/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-23 15:09
 */
package cn.acooly.sdk.filecoin.rpclient;

import cn.acooly.sdk.filecoin.domain.FilMessage;
import cn.acooly.sdk.filecoin.domain.FilSignMessage;
import cn.acooly.sdk.filecoin.domain.JsonRpcRequest;
import cn.acooly.sdk.filecoin.domain.JsonRpcResponse;
import cn.acooly.sdk.filecoin.rpclient.message.FilSignMessageResponse;
import cn.acooly.sdk.filecoin.transport.JsonRpcTransport;
import com.acooly.core.common.exception.BusinessException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-06-23 15:09
 */
@Slf4j
public class FileCoinMpool extends AbstractFileCoinRpcApiClient {

    /**
     * 获取钱包的最新Nonec
     *
     * @return
     */
    public Integer mpoolGetNonce(String address) {
        JsonRpcRequest request = new JsonRpcRequest("Filecoin.MpoolGetNonce");
        request.addParam(address);
        JsonRpcResponse<Integer> response = jsonRpcTransport.send(request, JsonRpcResponse.class);
        if (response.isSuccess()) {
            return response.getResultObject();
        }
        throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
    }

    /**
     * 预计算和签名消息
     *
     * <li>该方法可完成gas的计算</li>
     * <li>该方法中获取的nonce参数无效，在正式push消息时需要重新获取和计算签名</li>
     *
     * @param filMessage
     * @return
     */
    public FilSignMessage mpoolPushMessage(FilMessage filMessage) {
        JsonRpcRequest request = new JsonRpcRequest("Filecoin.MpoolPushMessage");
        request.addParam(filMessage);
        Map<String, Object> map = Maps.newHashMap();
        map.put("MaxFee", "0");
        request.addParam(map);
        FilSignMessageResponse response = jsonRpcTransport.send(request, FilSignMessageResponse.class);
        if (response.isSuccess()) {
            return response.getResultObject();
        }
        throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
    }


    /**
     * 发送已签名的交易
     * 类似：send命令的发送
     *
     * @param filSignMessage
     * @return
     */
    public String mpoolPush(FilSignMessage filSignMessage) {
        JsonRpcRequest request = new JsonRpcRequest("Filecoin.MpoolPush");
        request.addParam(filSignMessage);
        FilSignMessageResponse response = jsonRpcTransport.send(request, FilSignMessageResponse.class);
        if (response.isSuccess()) {
            JSONObject jsonObject = JSON.parseObject(response.getResult());
            return jsonObject.getString("/");
        }
        throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
    }


    public FileCoinMpool() {
        super();
    }

    public FileCoinMpool(JsonRpcTransport jsonRpcTransport) {
        super(jsonRpcTransport);
    }


}
