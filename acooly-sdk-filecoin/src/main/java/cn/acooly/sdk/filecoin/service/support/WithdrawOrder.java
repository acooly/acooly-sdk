/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-12 10:30
 */
package cn.acooly.sdk.filecoin.service.support;

import com.acooly.core.utils.BigMoney;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 提币请求报文
 * @author zhangpu
 * @date 2021-07-12 10:30
 */
@Data
@ToString
@Builder
public class WithdrawOrder {

    /**
     * 被提币（转出）钱包地址
     * 必须是我方托管钱包
     */
    private String from;
    /**
     * 提币目标（转入）钱包地址
     * 任意钱包
     */
    private String to;

    /**
     * 提币额度
     * 最小精度小数点后8位
     */
    private BigMoney amount;

    /**
     * 附加参数
     */
    private String params;

}
