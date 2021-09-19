/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-16 23:09
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.enums.SwftApiEnums;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询币种列表 请求报文
 *
 * @author zhangpu
 * @date 2021-09-16 23:09
 */

@Data
public class QueryCoinListRequest extends SwftRequest {
    /**
     * advanced：返回只支持跨链兑换的币种，不传或传其他值返回所有币种
     */
    private String supportType = "advanced";

    public QueryCoinListRequest() {
        setService(SwftApiEnums.QueryCoinList.service());
    }

    public QueryCoinListRequest(String supportType) {
        this();
        this.supportType = supportType;
    }
}
