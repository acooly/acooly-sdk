/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-16 23:09
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.message.dto.QueryCoinListInfo;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 查询币种列表 响应报文
 *
 * @author zhangpu
 * @date 2021-09-16 23:09
 */
@Data
public class QueryCoinListResponse extends SwftResponse {

    @JSONField(name = "data")
    private List<QueryCoinListInfo> queryCoinListInfos;
}
