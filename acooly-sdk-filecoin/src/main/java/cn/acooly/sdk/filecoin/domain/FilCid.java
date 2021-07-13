/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-29 11:20
 */
package cn.acooly.sdk.filecoin.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangpu
 * @date 2021-06-29 11:20
 */
@Data
@ToString
public class FilCid {

    @JSONField(name = "/")
    private String cid;
}
