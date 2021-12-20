/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-20 14:00
 */
package cn.acooly.sdk.swft.message.dto;

import com.acooly.core.common.facade.InfoBase;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author zhangpu
 * @date 2021-12-20 14:00
 */
@Data
public class TradeOrderPageInfo extends InfoBase {

    /**
     * 当前页
     * eg：1
     */
    @NotNull
    private Integer pageNo;

    /**
     * 每页显示记录数
     * eg：10
     */
    @NotNull
    private Integer pageSize;

    /**
     * 总页数
     * eg：10
     */
    @NotNull
    private Integer totalPage;

    /**
     * 总记录数
     * eg：100
     */
    @NotNull
    private Integer totalCount;

    /**
     * 数据列表
     * eg：ETH
     */
    @NotNull
    @JSONField(name = "pageContent")
    private List<TradeOrderInfo> tradeOrderInfos;
}
