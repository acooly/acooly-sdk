/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-12 11:26
 */
package cn.acooly.sdk.filecoin.service;

import cn.acooly.sdk.filecoin.domain.FilMessage;
import cn.acooly.sdk.filecoin.domain.FilSignMessage;
import cn.acooly.sdk.filecoin.rpclient.FileCoinMpool;
import cn.acooly.sdk.filecoin.rpclient.FileCoinWallet;
import cn.acooly.sdk.filecoin.service.support.WithdrawOrder;
import cn.acooly.sdk.filecoin.service.support.WithdrawResult;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author zhangpu
 * @date 2021-07-12 11:26
 */
@Slf4j
@NoArgsConstructor
public class FileCoinTransactionImpl implements FileCoinTransaction {

    private FileCoinWallet fileCoinWallet;
    private FileCoinMpool fileCoinMpool;


    @Override
    public WithdrawResult withdraw(WithdrawOrder order) {
        FilMessage filMessage = buildFilMessage(order);
        FilSignMessage filSignMessage = fileCoinMpool.mpoolPushMessage(filMessage);
        Integer nonce = fileCoinMpool.mpoolGetNonce(filMessage.getFrom());
        filMessage = filSignMessage.getFilMessage();
        filMessage.setNonce(nonce);
        filSignMessage = fileCoinWallet.walletSignMessage(filMessage);
        String messageId = fileCoinMpool.mpoolPush(filSignMessage);
        WithdrawResult result = new WithdrawResult();
        result.setMessageId(messageId);
        result.setFilSignMessage(filSignMessage);
        return result;
    }


    private FilMessage buildFilMessage(WithdrawOrder order) {
        BigDecimal value = order.getAmount().getAmount().multiply(BigDecimal.valueOf(Math.pow(10, 18)));
        FilMessage filMessage = new FilMessage();
        filMessage.setFrom(order.getFrom());
        filMessage.setTo(order.getTo());
        filMessage.setParams(order.getParams());
        filMessage.setValue(value.toPlainString());
        return filMessage;
    }

    public FileCoinTransactionImpl(FileCoinWallet fileCoinWallet, FileCoinMpool fileCoinMpool) {
        this.fileCoinWallet = fileCoinWallet;
        this.fileCoinMpool = fileCoinMpool;
    }

    public void setFileCoinWallet(FileCoinWallet fileCoinWallet) {
        this.fileCoinWallet = fileCoinWallet;
    }

    public void setFileCoinMpool(FileCoinMpool fileCoinMpool) {
        this.fileCoinMpool = fileCoinMpool;
    }
}
