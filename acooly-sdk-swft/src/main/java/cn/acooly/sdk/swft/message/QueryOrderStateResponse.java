/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-19 15:58
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.message.dto.QueryOrderStateInfo;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangpu
 * @date 2021-09-19 15:58
 */
@Data
public class QueryOrderStateResponse extends SwftResponse {

    @JSONField(name = "data")
    private QueryOrderStateInfo queryOrderStateInfo;

}
