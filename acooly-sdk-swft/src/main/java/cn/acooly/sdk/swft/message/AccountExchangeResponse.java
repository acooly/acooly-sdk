/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 22:26
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.message.dto.AccountExchangeInfo;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 闪兑接口 响应报文
 *
 * @author zhangpu
 * @date 2021-09-17 22:26
 */
@Data
public class AccountExchangeResponse extends SwftResponse {

    /**
     * 报文体
     */
    @JSONField(name = "data")
    private AccountExchangeInfo accountExchangeInfo;


}
