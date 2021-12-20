/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-20 13:49
 */
package cn.acooly.sdk.swft.message.dto;

import com.acooly.core.common.facade.InfoBase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 批量查询订单明细记录对象
 *
 * @author zhangpu
 * @date 2021-12-20 13:49
 */
@Data
public class TradeOrderInfo extends InfoBase {

    @Size(max = 30)
    @NotBlank
    private String orderId;

    /**
     * 兑换开始日期
     * eg：2017-09-08
     */
    @Size(max = 30)
    @NotBlank
    private String beginDate;

    /**
     * 原币币种
     * eg：ETH
     */
    @Size(max = 30)
    @NotBlank
    private String fromCoinCode;


    /**
     * 原币数量
     * eg：0.01
     */
    @NotNull
    private BigDecimal fromCoinAmt;

    /**
     * 目标币币种
     * eg：BTC
     */
    @Size(max = 30)
    @NotBlank
    private String toCoinCode;


    /**
     * 目标币数量
     * eg：0.14
     */
    @NotNull
    private BigDecimal toCoinAmt;

    /**
     * 手续费币种
     * eg：ETH
     */
    @Size(max = 30)
    @NotBlank
    private String feeCoinCode;

    /**
     * 手续费数量
     * eg：0.0003
     */
    @NotNull
    private BigDecimal feeCoinAmt;


    /**
     * 订单状态
     */
    @Size(max = 30)
    @NotBlank
    private String detailState;

    /**
     * 订单状态说明
     * 根据`detailState`值和文档翻译中文说明
     */
    private String detailStateText;


    /**
     * 兑换状态
     * wait_deposits：待存币 、
     * exchange：交换中
     * complete：完成（兑换成功）
     * timeout：超时、
     * wait_refund：兑换失败，待退币、
     * refund_complete：已退币
     */
    @Size(max = 50)
    @NotBlank
    private String tradeState;

    private String tradeFlag;

    /**
     * 链上费用（手续费）
     */
    private BigDecimal chainFee;

    /**
     * 平台收取的存入币的手续费，一般千分之二
     */
    private BigDecimal depositFeeRate;

}
