/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 22:30
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.enums.SwftApiEnums;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 查询兑换记录接口 请求报文
 * api/v2/queryAllTrade
 *
 * @author zhangpu
 * @date 2021-09-17 22:30
 */
@Data
public class QueryAllTradeRequest extends SwftRequest {
    /**
     * 设备编号
     * 设备唯一编号
     */
    @Size(max = 100)
    @NotBlank
    private String equipmentNo;

    /**
     * 设备来源
     * ANDROID,IOS,H5
     */
    @Size(max = 30)
    @NotBlank
    private String sourceType;

    /**
     * 当前页
     * eg：1，默认1
     */
    private int pageNo = 1;

    /**
     * 每页显示记录数
     * eg：10，默认10
     */
    private int pageSize = 10;

    public QueryAllTradeRequest() {
        setService(SwftApiEnums.QueryAllTrade.service());
    }

    public QueryAllTradeRequest(@Size(max = 100) @NotBlank String equipmentNo, @Size(max = 30) @NotBlank String sourceType) {
        this();
        this.equipmentNo = equipmentNo;
        this.sourceType = sourceType;
    }
}
