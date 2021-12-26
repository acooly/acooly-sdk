/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-25 23:23
 */
package cn.acooly.sdk.coinapi.platform.coinmarketcap.sdk.message;

import com.acooly.core.common.facade.InfoBase;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangpu
 * @date 2021-12-25 23:23
 */
@Data
public class CoinmarketcapStatus extends InfoBase {

    private Date timestamp;

    @JSONField(name = "error_code")
    private int errorCode;

    @JSONField(name = "error_message")
    private String errorMessage;

    private int elapsed;

    @JSONField(name = "credit_count")
    private int creditCount;

}
