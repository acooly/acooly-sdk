/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-10 11:50
 */
package cn.acooly.sdk.filecoin.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangpu
 * @date 2021-07-10 11:50
 */
@Data
@ToString
public class FilSignature {

    @JSONField(name = "Type")
    private Integer type;

    @JSONField(name = "Data")
    private String data;

}
