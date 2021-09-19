/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-16 23:09
 */
package cn.acooly.sdk.swft.message.dto;

import com.acooly.core.common.facade.InfoBase;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 查询币种列表 响应报文
 *
 * @author zhangpu
 * @date 2021-09-16 23:09
 */
@Data
public class QueryCoinListInfo extends InfoBase {
    /**
     * 货币全称
     * Bitcoin
     */
    @Size(max = 50)
    @NotBlank
    private String coinAllCode;

    /**
     * 货币编码
     * BTC
     */
    @Size(max = 30)
    @NotBlank
    private String coinCode;

    /**
     * 货币名称
     * 比特币
     */
    @Size(max = 100)
    @NotBlank
    private String coinName;

    /**
     * 是否支持高级兑换
     * Y：支持跨链兑换、N:不支持跨链兑换
     */
    @Size(max = 2)
    @NotBlank
    private String isSupportAdvanced;

    /**
     * 货币图像
     */
    @Size(max = 500)
    private String coinImage;

    /**
     * 代币所属主网(主网简称)
     * 比如 SWFTC是以太坊上的代币，则mainNetwork为ETH；OCT属于EOS链上的代币，则mainNetwork为EOS；
     * 只有该币种属于某个主链上的代币时，该字段才有值，像BTC、ETH、EOS等主网币种在该字段上返回为空
     */
    @Size(max = 30)
    private String mainNetwork;

    /**
     * 可以在主网上标识该唯一代币的 合约地址或合约名称
     * 只有该币种属于某个主链上的代币时，该字段才有值
     */
    @Size(max = 100)
    private String contact;

    /**
     * 不支持的币种
     * 1.如无则为空，例如："noSupportCoin":""
     * 2.如有多个不支持币种，用‘，’隔开。
     * 例如："noSupportCoin":"TKT,SHE,AIDOC"
     */
    @Size(max = 500)
    private String noSupportCoin;
}
