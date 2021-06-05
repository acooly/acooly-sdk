/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-05 12:50
 */
package cn.acooly.sdk.coinapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * FIL计算器 计算请求参数
 *
 * @author zhangpu
 * @date 2021-06-05 12:50
 */
@Getter
@Setter
public class FilCalcRequest {

    /**
     * 存储空间（T）
     */
    private int power;

    /**
     * 填满时间（天数）
     */
    private int fillTime;

    /**
     * 开挖日期
     */
    private String workStartDate;

    /**
     * 一年后算力（P）
     */
    private BigDecimal yearPower;

}
