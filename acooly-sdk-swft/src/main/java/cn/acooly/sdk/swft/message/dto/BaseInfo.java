/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 22:07
 */
package cn.acooly.sdk.swft.message.dto;

import cn.acooly.sdk.swft.message.SwftResponse;
import com.acooly.core.common.facade.DtoBase;
import com.acooly.core.common.facade.InfoBase;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * 汇率信息
 * <p>
 * 计算用户兑换实际到账数量：
 * 实际到账数量 = （用户存币数量 - 兑换手续费数量）* 汇率 -  链上发币手续费
 * receiveCoinAmt = （depositCoinAmt - depositCoinAmt * 兑换手续费率） *  instantRate - receiveCoinFee
 *
 * @author zhangpu
 * @date 2021-09-17 22:07
 */
@Data
public class BaseInfo extends InfoBase {

    /**
     * 链上手续费，同receiveCoinFee（目标币）
     */
    private BigDecimal chainFee;

    /**
     * 平台收取的存入币的手续费，一般千分之二
     */
    private BigDecimal depositCoinFeeRate;

    /**
     * 即时汇率
     * "精确到小数点后十位
     * 接收货币/存入货币的汇率"
     */
    @NotEmpty
    private BigDecimal instantRate;

    /**
     * ?
     */
    private BigDecimal minerFee;

    /**
     * ?
     */
    private String isDisCount;


    /**
     * 链上发币手续费
     * <p>
     * 该值为兑换成功后发币时扣取的网络手续费数量，单位为接收币币种，可用于提前计算用户大致接收到的币的数量，或用于显示用户即将扣除发币网络手续费的数量
     */
    private BigDecimal receiveCoinFee;

    /**
     * 最低存储额
     * 精确到小数点后六位
     */
    @NotEmpty
    private BigDecimal depositMin;

    /**
     * 最高存储额
     * 精确到小数点后六位
     */
    @NotEmpty
    private BigDecimal depositMax;
}
