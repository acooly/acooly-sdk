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
import cn.acooly.sdk.filecoin.enums.KeyType;
import cn.acooly.sdk.filecoin.rpclient.message.FilSignMessageResponse;
import cn.acooly.sdk.filecoin.rpclient.message.WalletListRpcResponse;
import cn.acooly.sdk.filecoin.transport.JsonRpcTransport;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Strings;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangpu
 * @date 2021-06-23 15:09
 */
@Slf4j
public class FileCoinWallet extends AbstractFileCoinRpcApiClient {

    /**
     * 创建新钱包，并返回钱包地址
     *
     * @return
     */
    public String walletNew(KeyType keyType) {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod("Filecoin.WalletNew");
        request.addParam(keyType.code());
        JsonRpcResponse response = jsonRpcTransport.send(request, JsonRpcResponse.class);
        if (response.isSuccess()) {
            return response.getResult();
        }
        throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
    }


    /**
     * 列表钱包地址
     *
     * @return
     */
    public List<String> walletList() {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setMethod("Filecoin.WalletList");
        WalletListRpcResponse response = jsonRpcTransport.send(request, WalletListRpcResponse.class);
        if (response.isSuccess()) {
            return response.getResultObject();
        }
        throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
    }


    /**
     * 查询钱包余额
     * <p>
     * 注意返回的数据是小数点后18位精度，并乘以10^18实现整数化，所有本地获取数据后，需除以10^18还原
     *
     * @param address
     * @return
     */
    public BigDecimal walletBalance(String address) {
        JsonRpcRequest request = new JsonRpcRequest("Filecoin.WalletBalance");
        request.addParam(address);
        JsonRpcResponse response = jsonRpcTransport.send(request, JsonRpcResponse.class);
        if (response.isSuccess()) {
            String balanceResult = response.getResult();
            if (Strings.isNumber(balanceResult)) {
                BigDecimal bigDecimal = new BigDecimal(balanceResult);
                if (bigDecimal.equals(BigDecimal.ZERO)) {
                    return bigDecimal;
                }
                BigDecimal balance = bigDecimal.divide(new BigDecimal(Math.pow(10, 18)),
                        8, BigDecimal.ROUND_HALF_UP);
                return balance;
            }
        }
        throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
    }


    public FilSignMessage walletSignMessage(FilMessage filMessage) {
        JsonRpcRequest request = new JsonRpcRequest("Filecoin.WalletSignMessage");
        request.addParam(filMessage.getFrom());
        request.addParam(filMessage);
        FilSignMessageResponse response = jsonRpcTransport.send(request, FilSignMessageResponse.class);
        if (response.isSuccess()) {
            return response.getResultObject();
        }
        throw new BusinessException(response.getErrorObject(), response.getErrorObject().getData());
    }


    public FileCoinWallet() {
        super();
    }

    public FileCoinWallet(JsonRpcTransport jsonRpcTransport) {
        super(jsonRpcTransport);
    }


}
