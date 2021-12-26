/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by zhangpu
 * date:2021-12-25
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap.dbcache.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import com.acooly.core.common.domain.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 币报价 Entity
 *
 * @author zhangpu
 * @date 2021-12-25 22:22:46
 */
@Entity
@Table(name = "coinapi_coinmarketcap_quotes")
@Getter
@Setter
public class CoinmarketcapQuotes extends AbstractEntity {

    /**
     * 币种编码
     */
    @NotBlank
    @Size(max = 16)
    private String coinCode;

    /**
     * 更新时间毫秒
     */
    @NotNull
    private Long lastUpdatedMillis;

    /**
     * 价格更新时间
     */
    private Date lastUpdated;

    /**
     * 当前价格（USD）
     */
    @NotNull
    private BigDecimal price;

    /**
     * 24小时交易量
     */
    @Column(name = "volume_24h")
    private BigDecimal volume24h;

    /**
     * 24小时交易量变化
     */
    @Column(name = "volume_change_24h")
    private BigDecimal volumeChange24h;

    /**
     * 价格1小时变化比
     */
    @Column(name = "percent_change_1h")
    private BigDecimal percentChange1h;

    /**
     * 价格24小时变化比
     */
    @Column(name = "percent_change_24h")
    private BigDecimal percentChange24h;

    /**
     * 价格7天变化比
     */
    @Column(name = "percent_change_7d")
    private BigDecimal percentChange7d;

    /**
     * 价格30天变化比
     */
    @Column(name = "percent_change_30d")
    private BigDecimal percentChange30d;

    /**
     * 市值
     */
    private BigDecimal marketCap;

    /**
     * 市场占有率
     */
    private BigDecimal marketCapDominance;

    /**
     * 稀释后市值
     */
    private BigDecimal fullyDilutedMarketCap;

    /**
     * 备注
     */
    @Size(max = 128)
    private String comments;

}
