/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 22:26
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.enums.SwftApiEnums;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 闪兑接口 请求报文
 *
 * @author zhangpu
 * @date 2021-09-17 22:26
 */
@Data
@ToString
public class AccountExchangeRequest extends SwftRequest {
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
     * eg：0.01
     */
    @Size(max = 50)
    @NotBlank
    private String depositCoinAmt;

    /**
     * 接收币数量
     * eg：0.001
     */
    @Size(max = 50)
    @NotBlank
    private String receiveCoinAmt;

    /**
     * 收币地址
     * eg: 18orDLFMp3fGoy5Uk93LDGTGbxWEm7b7FY
     * 如有memo,请讲memo放到地址后，用#分隔，例如：18orDLFMp3fGoy5Uk93LDGTGbxWEm7b7FY#1927632
     */
    @Size(max = 50)
    @NotBlank
    private String destinationAddr;

    /**
     * 退币地址
     * eg：18orDLFMp3fGoy5Uk93LDGTGbxWEm7b7FY
     * 如有memo,请讲memo放到地址后，用#分隔，例如：18orDLFMp3fGoy5Uk93LDGTGbxWEm7b7FY#1927632
     */
    @Size(max = 50)
    @NotBlank
    private String refundAddr;

    /**
     * 设备编号
     * 设备唯一编号
     */
    @Size(max = 100)
    @NotBlank
    private String equipmentNo;

    /**
     * 设备来源
     * ANDROID,IOS,H5
     */
    @Size(max = 30)
    @NotBlank
    private String sourceType;

    /**
     * 订单创建来源
     * 不填写则使用配置参数
     * 用于标识是哪个平台创建的订单，需要与SWFT BlockChain进行协商设置
     */
    @Size(max = 50)
    private String sourceFlag;

    public AccountExchangeRequest() {
        setService(SwftApiEnums.AccountExchange.service());
    }
}
