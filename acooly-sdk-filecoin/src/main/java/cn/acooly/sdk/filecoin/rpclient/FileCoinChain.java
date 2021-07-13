/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-23 15:09
 */
package cn.acooly.sdk.filecoin.rpclient;

import cn.acooly.sdk.filecoin.transport.JsonRpcTransport;
import cn.acooly.sdk.filecoin.domain.JsonRpcRequest;
import cn.acooly.sdk.filecoin.domain.JsonRpcResponse;
import cn.acooly.sdk.filecoin.rpclient.message.ChainGetBlockMessagesRpcResponse;
import cn.acooly.sdk.filecoin.rpclient.message.ChainGetTipSetByHeightRpcResponse;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Collections3;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-06-23 15:09
 */
@Slf4j
public class FileCoinChain extends AbstractFileCoinRpcApiClient {

    /**
     * 创建新钱包，并返回钱包地址
     *
     * @return
     */
    public Integer getBlockHeight() {
        JsonRpcRequest request = new JsonRpcRequest("Filecoin.ChainHead");
        JsonRpcResponse response = jsonRpcTransport.send(request, JsonRpcResponse.class);
        if (response.isSuccess()) {
            JSONObject object = JSONObject.parseObject(response.getResult());
            if (object != null) {
                return object.getInteger("Height");
            }
            return 0;
        }
        throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
    }


    /**
     * 查询指定高度的区块CID
     *
     * @return
     */
    public ChainGetTipSetByHeightRpcResponse.ChainGetTipSetByHeight chainGetTipSetByHeight(Integer blockHeight) {

        List<Object> params = Lists.newArrayList();
        params.add(blockHeight);
        params.add(Lists.newArrayList());
        JsonRpcRequest request = new JsonRpcRequest("Filecoin.ChainGetTipSetByHeight", params);
        ChainGetTipSetByHeightRpcResponse response = jsonRpcTransport.send(request, ChainGetTipSetByHeightRpcResponse.class);
        if (!response.isSuccess()) {
            throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
        }

        ChainGetTipSetByHeightRpcResponse.ChainGetTipSetByHeight chainGetTipSetByHeight = response.getResultObject();
        JSONObject jsonObject = JSON.parseObject(response.getResult());
        JSONArray jsonArray = jsonObject.getJSONArray("Cids");
        if (Collections3.isNotEmpty(jsonArray)) {
            for (Object o : jsonArray) {
                JSONObject cid = (JSONObject) o;
                chainGetTipSetByHeight.getFilCids().add(cid.getString("/"));
            }
        }
        return chainGetTipSetByHeight;

    }

    /**
     * 查询指定CID的区块内消息
     *
     * @param cid
     * @return
     */
    public ChainGetBlockMessagesRpcResponse.ChainGetBlockMessages chainGetBlockMessages(String cid) {

        List<Object> params = Lists.newArrayList();
        Map<String, String> param = Maps.newHashMap();
        param.put("/", cid);
        params.add(param);
        JsonRpcRequest request = new JsonRpcRequest("Filecoin.ChainGetBlockMessages", params);
        ChainGetBlockMessagesRpcResponse response = jsonRpcTransport.send(request, ChainGetBlockMessagesRpcResponse.class);
        if (!response.isSuccess()) {
            throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
        }
        return response.getResultObject();

    }


    public FileCoinChain() {
        super();
    }

    public FileCoinChain(JsonRpcTransport jsonRpcTransport) {
        super(jsonRpcTransport);
    }


}
