/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-12 11:20
 */
package cn.acooly.sdk.filecoin.service.support;

import cn.acooly.sdk.filecoin.domain.FilSignMessage;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangpu
 * @date 2021-07-12 11:20
 */
@Data
@ToString
public class WithdrawResult {

    /**
     * 消息ID
     */
    private String messageId;

    FilSignMessage filSignMessage;

}
