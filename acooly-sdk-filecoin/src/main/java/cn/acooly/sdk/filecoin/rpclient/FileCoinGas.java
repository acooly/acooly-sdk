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
import cn.acooly.sdk.filecoin.domain.FilMessage;
import cn.acooly.sdk.filecoin.domain.JsonRpcRequest;
import cn.acooly.sdk.filecoin.domain.JsonRpcResponse;
import com.acooly.core.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

/**
 * @author zhangpu
 * @date 2021-06-23 15:09
 */
@Slf4j
public class FileCoinGas extends AbstractFileCoinRpcApiClient {

    /**
     * 估算gas费用的封顶值
     * GasFeeCap
     *
     * @return
     */
    public String gasEstimateFeeCap(FilMessage message) {
        JsonRpcRequest request = new JsonRpcRequest("Filecoin.GasEstimateFeeCap");
        request.addParam(message);
        request.addParam(100);
        request.addParam(null);
        JsonRpcResponse response = jsonRpcTransport.send(request, JsonRpcResponse.class);
        if (response.isSuccess()) {
            String result = response.getResult();
//            result = new BigDecimal(result).multiply(new BigDecimal("0.12")).divide(new BigDecimal("10000"), 0, BigDecimal.ROUND_HALF_UP).toPlainString();
            return result;
        }
        throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
    }


    public BigInteger gasEstimateGasLimit(FilMessage message) {
        JsonRpcRequest request = new JsonRpcRequest("Filecoin.GasEstimateGasLimit");
        request.addParam(message);
        request.addParam(null);
        JsonRpcResponse response = jsonRpcTransport.send(request, JsonRpcResponse.class);
        if (response.isSuccess()) {
            String result = response.getResult();
            return new BigInteger(result);
        }
        throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
    }




    public FileCoinGas() {
        super();
    }

    public FileCoinGas(JsonRpcTransport jsonRpcTransport) {
        super(jsonRpcTransport);
    }


}
