/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-26 15:21
 */
package cn.acooly.sdk.filecoin.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author zhangpu
 * @date 2021-08-26 15:21
 */
@Data
@ToString
public class SecpkMessage {

    @JSONField(name = "Message")
    private FilMessage filMessage;

    @JSONField(name = "Signature")
    private FilSignature filSignature;
}
