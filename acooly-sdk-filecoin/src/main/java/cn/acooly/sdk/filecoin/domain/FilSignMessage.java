/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-29 13:41
 */
package cn.acooly.sdk.filecoin.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-06-29 13:41
 */
@Data
@ToString
public class FilSignMessage {

    @JSONField(name = "Message")
    private FilMessage filMessage;

    @JSONField(name = "Signature")
    private FilSignature filSignature;

    @JSONField(name = "CID")
    private Map<String, String> cids = Maps.newHashMap();

    @JSONField(serialize = false)
    public String getCID() {
        return cids.get("/");
    }

    @JSONField(deserialize = false)
    private void setCID(String cid) {
        this.cids.put("/", cid);
    }

    public FilSignMessage() {
    }
}
