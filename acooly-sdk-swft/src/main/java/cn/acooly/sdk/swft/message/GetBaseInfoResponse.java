/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 22:07
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.message.dto.BaseInfo;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author zhangpu
 * @date 2021-09-17 22:07
 */
@Data
public class GetBaseInfoResponse extends SwftResponse {

    @JSONField(name = "data")
    private BaseInfo baseInfo;

}
