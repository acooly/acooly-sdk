/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-03 14:25
 */
package cn.acooly.sdk.coinrate.dto;

import com.acooly.core.common.facade.DtoBase;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 行情数据
 * @author zhangpu
 * @date 2021-06-03 14:25
 */
@Getter
@Setter
public class Ticker extends DtoBase {

    /**
     * 兑换标志
     * 格式为${from币缩写}${to币缩写}，例如：btcusdt,filusdt
     */
    private String symbol;

    /**
     * 24小时交易额
     */
    private BigDecimal volume;
    /**
     * 24小时成交笔数
     */
    private int count;

    /**
     * 24小时成交数量（币数量）
     */
    private BigDecimal amount;

    /**
     * 24小时最低价
     */
    private BigDecimal low;

    /**
     * 24小时最高价
     */
    private BigDecimal high;

    /**
     * 收盘价和最新成交价
     */
    private BigDecimal close;

    /**
     * 24小时前开盘价
     */
    private BigDecimal open;

    /**
     * 交易记录时间
     */
    private Date time;




}
