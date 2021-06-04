/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-03 14:25
 */
package cn.acooly.sdk.coinapi.dto;

import com.acooly.core.common.facade.DtoBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 行情数据
 *
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
     * 24小时交易金额额
     */
    @JsonProperty("volume")
    private BigDecimal baseVolume;

    /**
     * 24小时成交数量（币数量）
     */
    @JsonProperty("amount")
    private BigDecimal quoteVolume;

    /**
     * 24小时成交笔数
     */
    private BigDecimal count;

    /**
     * 24小时最低价
     */
    @JsonProperty("low")
    private BigDecimal lowPrice;

    /**
     * 24小时最高价
     */
    @JsonProperty("high")
    private BigDecimal highPrice;

    /**
     * 收盘价和最新成交价
     */
    @JsonProperty("close")
    private BigDecimal lastPrice;

    /**
     * 24小时前开盘价
     */
    @JsonProperty("open")
    private BigDecimal openPrice;

    /**
     * 交易记录时间
     */
    private Date time;


}
