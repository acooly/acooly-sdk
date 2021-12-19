/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 22:26
 */
package cn.acooly.sdk.swft.message.dto;

import cn.acooly.sdk.swft.message.SwftResponse;
import com.acooly.core.common.facade.InfoBase;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 闪兑接口 响应报文
 *
 * @author zhangpu
 * @date 2021-09-17 22:26
 */
@Data
public class AccountExchangeInfo extends InfoBase {

    /**
     * 订单号
     * eg：d47e8b9b-c17f-432b-9285-a46c0a3ceb9a
     */
    @Size(max = 30)
    @NotBlank
    private String orderId;

    /**
     * 存币币种
     * eg：ETH
     */
    @Size(max = 30)
    @NotBlank
    private String depositCoinCode;

    /**
     * 接收币币种
     * eg：BTC
     */
    @Size(max = 30)
    @NotBlank
    private String receiveCoinCode;

    /**
     * 存币数量
     * eg：1
     */
    @NotNull
    private BigDecimal depositCoinAmt;

    /**
     * 接收币数量
     * eg：0.1
     */
    @NotNull
    private BigDecimal receiveCoinAmt;

    /**
     * 存币地址
     * eg：123123123-232-1231232
     */
    @Size(max = 50)
    @NotBlank
    private String platformAddr;

    /**
     * 目标币接收地址
     * eg: 18orDLFMp3fGoy5Uk93LDGTGbxWEm7b7FY,
     * 如有memo,请讲memo放到地址后，用#分隔，例如：18orDLFMp3fGoy5Uk93LDGTGbxWEm7b7FY#1927632
     */
    @Size(max = 50)
    @NotBlank
    private String destinationAddr;

    /**
     * 退原币的地址
     * eg: 18orDLFMp3fGoy5Uk93LDGTGbxWEm7b7FY
     * 如有memo,请讲memo放到地址后，用#分隔，例如：18orDLFMp3fGoy5Uk93LDGTGbxWEm7b7FY#1927632
     */
    @Size(max = 50)
    @NotBlank
    private String refundAddr;

    /**
     * 存币的手续费率
     * eg：手续费率
     */
    @NotNull
    private BigDecimal depositCoinFeeRate;

    /**
     * 存币的手续费金额
     * eg：手续费收取的原币的数量
     */
    @NotNull
    private BigDecimal depositCoinFeeAmt;

    /**
     * 退币金额
     * eg: 0.98
     */
    private BigDecimal refundCoinAmt;

    /**
     * 兑换成功交易id
     * 链上交易id，在兑换完成并已发币之后，该字段才会有值
     */
    @Size(max = 100)
    private String transactionId;

    /**
     * 兑换失败交易id
     * 链上交易id，在兑换失败退币情况下，已发币之后，该字段才会有值
     */
    @Size(max = 100)
    private String refundDepositTxid;

    /**
     * 订单状态
     * (1)wait_deposit_send:等待存币发送
     * (2)timeout:超时；
     * (3)wait_exchange_push:等待交换信息推送；
     * (4)wait_exchange_return:等待交换信息返回；
     * (5.1)wait_receive_send:等待接收币种发送, wait_receive_confirm:等待接收币种确认, receive_complete:接收币种确认完成.
     * (5.2)wait_refund_send:等待退原币币种发送, wait_refund_confirm:等待退原币币种确认, refund_complete:退原币币种确认完成；
     * (6)ERROR/error:正在处理的订单
     * (7)WAIT_KYC: 等待进行KYC或联系客服提供链接
     */
    @Size(max = 30)
    @NotBlank
    private String detailState;

    /**
     * 订单状态
     * eg：wait_deposits
     */
    @Size(max = 30)
    @NotBlank
    private String orderState;

    /**
     * 兑换方式
     * simple：简单兑换、advanced：高级兑换
     */
    @Size(max = 20)
    @NotBlank
    private String changeType;

    /**
     * kyc的路径
     * 当返回码是311时,需要跳转到该链接。
     * https://swap.swftcoin.com/swft-v3/swft-v3-m/kyc/kyc.html?lang=cn&equipmentNo=pls_input_your_real_equipmentno_ok
     * 请更新equipmentNo的值用户的设备号（公共请求参数），lang取值：cn、en
     */
    @Size(max = 128)
    private String kycUrl;

}
