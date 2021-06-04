/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-04 15:19
 */
package cn.acooly.sdk.coinapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author zhangpu
 * @date 2021-06-04 15:19
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MifengchaTicker {

    /**
     * 13-bit Unix Timestamp
     */
    @JsonProperty("T")
    private Long timestamp;

    /**
     * MarketPairDesc
     */
    @JsonProperty("m")
    private String marketPair;

    /**
     * 24H Open Price(unit：quoteCurrency)
     */
    @JsonProperty("o")
    private BigDecimal openPrice;

    /**
     * Last Price, Close Price(unit：quoteCurrency)
     */
    @JsonProperty("c")
    private BigDecimal lastPrice;

    /**
     * 24H Low Price(unit：quoteCurrency)
     */
    @JsonProperty("l")
    private BigDecimal lowPrice;

    /**
     * 24H High Price(unit：quoteCurrency)
     */
    @JsonProperty("h")
    private BigDecimal highPrice;

    /**
     * Best Ask Price(unit：quoteCurrency)
     */
    @JsonProperty("a")
    private BigDecimal askPrice;

    /**
     * Best Ask Amount(unit：quoteCurrency)
     */
    @JsonProperty("A")
    private BigDecimal askAmount;

    /**
     * Best Bid Price(unit：quoteCurrency)
     */
    @JsonProperty("b")
    private BigDecimal bidPrice;

    /**
     * Best Bid Amount(unit：quoteCurrency)
     */
    @JsonProperty("B")
    private BigDecimal bidAmount;

    /**
     * 24H Price Change Rate
     */
    @JsonProperty("C")
    private BigDecimal changeDaily;

    /**
     * 24H Base Volume(unit：baseCurrency)
     */
    @JsonProperty("bv")
    private BigDecimal baseVolume;

    /**
     * 24H Quote Volume(unit：quoteCurrency)
     */
    @JsonProperty("qv")
    private BigDecimal quoteVolume;

    /**
     * To Usd Rate
     */
    @JsonProperty("r")
    private BigDecimal usdRate;

    /**
     * Purity
     */
    @JsonProperty("p")
    private BigDecimal purity;

    /**
     * Spread
     */
    @JsonProperty("s")
    private BigDecimal spread;

}
