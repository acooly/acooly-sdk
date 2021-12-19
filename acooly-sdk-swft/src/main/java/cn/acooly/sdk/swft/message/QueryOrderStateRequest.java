/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-19 15:14
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.enums.SwftApiEnums;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 查询订单状态接口
 * /api/v2/queryOrderState
 *
 * @author zhangpu
 * @date 2021-09-19 15:14
 */
@Data
public class QueryOrderStateRequest extends SwftRequest {

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
     * 订单号
     * eg：1fc8499f-dd6d-4ff3-8b7f-7a0d74c59adc
     */
    @Size(max = 100)
    @NotBlank
    private String orderId;

    public QueryOrderStateRequest() {
        setService(SwftApiEnums.QueryOrderState.service());
    }

    public QueryOrderStateRequest(@Size(max = 100) @NotBlank String equipmentNo, @Size(max = 30) @NotBlank String sourceType, @Size(max = 100) @NotBlank String orderId) {
        this();
        this.equipmentNo = equipmentNo;
        this.sourceType = sourceType;
        this.orderId = orderId;
    }
}
