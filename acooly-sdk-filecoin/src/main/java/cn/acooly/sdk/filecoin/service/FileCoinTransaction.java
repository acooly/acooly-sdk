/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-12 10:21
 */
package cn.acooly.sdk.filecoin.service;

import cn.acooly.sdk.filecoin.service.support.WithdrawOrder;
import cn.acooly.sdk.filecoin.service.support.WithdrawResult;

/**
 * @author zhangpu
 * @date 2021-07-12 10:21
 */
public interface FileCoinTransaction {

    /**
     * 提币
     * <p>
     * 从指定钱包（from）转出币到目标钱包（to）
     * </p>
     * <p>
     * 核心逻辑：
     * <li>通过基本参数组装transfer（from,to,amount,params等）</li>
     * <li>通过FileCoin.mpoolPushMessage预计算gas相关参数，并设置到交易信息中</li>
     * <li>通过FileCoin.mpoolGetNonce获取转出钱包最新的Nonce参数，并设置到交易信息中</li>
     * <li>通过FileCoin.WalletSignMessage重新签名交易</li>
     * <li>通过FileCoin.mpoolPush发送交易上链生效</li>
     *
     * @param order
     * @return
     */
    WithdrawResult withdraw(WithdrawOrder order);

}
