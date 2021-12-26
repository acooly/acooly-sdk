/**
 * acooly-sdk
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-25 15:50
 */
package cn.acooly.sdk.coinapi.platform.binance.dto;

import com.acooly.core.common.facade.InfoBase;
import com.acooly.core.utils.BigMoney;
import lombok.Data;

/**
 * @author zhangpu
 * @date 2021-12-25 15:50
 */
@Data
public class BinanceCoinQuote extends InfoBase {

    private String symbol;
    private BigMoney price;
}
