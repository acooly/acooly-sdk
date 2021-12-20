/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 22:30
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.message.dto.TradeOrderPageInfo;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 查询兑换记录接口 响应报文
 * api/v2/queryAllTrade
 *
 * @author zhangpu
 * @date 2021-09-17 22:30
 */
@Data
public class QueryAllTradeResponse extends SwftResponse {

    @JSONField(name = "data")
    private TradeOrderPageInfo tradeOrderPageInfo;


}
