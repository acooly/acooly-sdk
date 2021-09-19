/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 22:05
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.enums.SwftApiEnums;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * 获取汇率接口 请求报文
 * /api/v1/getBaseInfo
 *
 * @author zhangpu
 * @date 2021-09-17 22:05
 */
@Data
@ToString
public class GetBaseInfoRequest extends SwftRequest {

    /**
     * 存入货币编码
     * BTC
     */
    @NotEmpty
    private String depositCoinCode;

    /**
     * 接收货币编码
     * ETH
     */
    @NotEmpty
    private String receiveCoinCode;

    public GetBaseInfoRequest() {
        setService(SwftApiEnums.GetBaseInfo.service());
    }

    public GetBaseInfoRequest(@NotEmpty String depositCoinCode, @NotEmpty String receiveCoinCode) {
        this();
        this.depositCoinCode = depositCoinCode;
        this.receiveCoinCode = receiveCoinCode;
    }
}
