/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-25 23:26
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap.sdk.message;

import com.acooly.core.common.facade.InfoBase;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangpu
 * @date 2021-12-25 23:26
 */
@Data
public class CoinmarketcapQuoteInfo extends InfoBase {

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
    @JSONField(name = "last_updated")
    private Date lastUpdated;

    /**
     * 当前价格（USD）
     */
    @NotNull
    private BigDecimal price;

    /**
     * 24小时交易量
     */
    @JSONField(name = "volume_24h")
    private BigDecimal volume24h;

    /**
     * 24小时交易量变化
     */
    @JSONField(name = "volumeChange_24h")
    private BigDecimal volumeChange24h;

    /**
     * 价格1小时变化比
     */
    @JSONField(name = "percent_change_1h")
    private BigDecimal percentChange1h;

    /**
     * 价格24小时变化比
     */
    @JSONField(name = "percent_change_24h")
    private BigDecimal percentChange24h;

    /**
     * 价格7天变化比
     */
    @JSONField(name = "percent_change_7d")
    private BigDecimal percentChange7d;

    /**
     * 价格30天变化比
     */
    @JSONField(name = "percent_change_30d")
    private BigDecimal percentChange30d;

    /**
     * 市值
     */
    @JSONField(name = "market_cap")
    private BigDecimal marketCap;

    /**
     * 市场占有率
     */
    @JSONField(name = "market_cap_dominance")
    private BigDecimal marketCapDominance;

    /**
     * 稀释后市值
     */
    @JSONField(name = "fully_diluted_market_cap")
    private BigDecimal fullyDilutedMarketCap;

}
