/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-29 13:41
 */
package cn.acooly.sdk.filecoin.domain;

import cn.acooly.sdk.filecoin.utils.FilecoinUtils;
import com.acooly.core.utils.BigMoney;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-06-29 13:41
 */
@Data
@ToString
@NoArgsConstructor
public class FilMessage {

    @JSONField(name = "Version")
    private int version = 0;

    @JSONField(name = "To")
    private String to;

    @JSONField(name = "From")
    private String from;

    @JSONField(name = "Nonce")
    private int nonce = 0;

    @JSONField(name = "Value")
    private String value;

    @JSONField(name = "GasLimit")
    private int gasLimit = 0;

    @JSONField(name = "GasFeeCap")
    private String gasFeeCap = "0";

    @JSONField(name = "GasPremium")
    private String gasPremium = "0";

    @JSONField(name = "Method")
    private int method = 0;

    @JSONField(name = "Params")
    private String params;

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

    public BigMoney getAmount() {
        return FilecoinUtils.toBigMoney(this.value);
    }


    public FilMessage(String from, String to, String value, String params) {
        this.to = to;
        this.from = from;
        this.value = value;
        this.params = params;
    }

    public FilMessage(String from, String to, String value) {
        this(from, to, value, null);
    }
}
