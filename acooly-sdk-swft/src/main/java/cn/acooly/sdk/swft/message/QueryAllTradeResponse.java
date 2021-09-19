/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 22:30
 */
package cn.acooly.sdk.swft.message;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 查询兑换记录接口 响应报文
 * api/v2/queryAllTrade
 *
 * @author zhangpu
 * @date 2021-09-17 22:30
 */
@Data
public class QueryAllTradeResponse extends SwftResponse {
    /**
     * 当前页
     * eg：1
     */
    @Size(max = 10)
    @NotBlank
    private String pageNo;

    /**
     * 每页显示记录数
     * eg：10
     */
    @Size(max = 10)
    @NotBlank
    private String pageSize;

    /**
     * 总页数
     * eg：10
     */
    @Size(max = 10)
    @NotBlank
    private String totalPage;

    /**
     * 总记录数
     * eg：100
     */
    @Size(max = 10)
    @NotBlank
    private String totalCount;

    /**
     * 数据结果
     * eg：[{name1:value1},{name1:value1},{name1:value1}]
     */
    @Size(max = 10)
    @NotBlank
    private String pageContent;

    /**
     * 原币币种
     * eg：ETH
     */
    @Size(max = 30)
    @NotBlank
    private String fromCoinCode;

    /**
     * 目标币币种
     * eg：BTC
     */
    @Size(max = 30)
    @NotBlank
    private String toCoinCode;

    /**
     * 原币数量
     * eg：0.01
     */
    @Size(max = 50)
    @NotBlank
    private String fromCoinAmt;

    /**
     * 目标币数量
     * eg：0.14
     */
    @Size(max = 50)
    @NotBlank
    private String toCoinAmt;

    /**
     * 兑换开始日期
     * eg：2017-09-08
     */
    @Size(max = 30)
    @NotBlank
    private String beginDate;

    /**
     * 手续费币种
     * eg：ETH
     */
    @Size(max = 30)
    @NotBlank
    private String feeCoinCode;

    /**
     * 手续费数量
     * eg：0.0003
     */
    @Size(max = 50)
    @NotBlank
    private String feeCoinAmt;

    /**
     * 订单号
     * eg：d47e8b9b-c17f-432b-9285-a46c0a3ceb9a
     */
    @Size(max = 50)
    @NotBlank
    private String orderId;

    /**
     * 兑换状态
     * wait_deposits：待存币 、
     * exchange：交换中
     * complete：完成（兑换成功）
     * timeout：超时、
     * wait_refund：兑换失败，待退币、
     * refund_complete：已退币
     */
    @Size(max = 50)
    @NotBlank
    private String tradeState;
}
