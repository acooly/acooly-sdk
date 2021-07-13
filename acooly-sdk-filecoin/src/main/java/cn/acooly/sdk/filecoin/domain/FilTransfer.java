/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-07 21:44
 */
package cn.acooly.sdk.filecoin.domain;

import com.acooly.core.utils.BigMoney;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangpu
 * @date 2021-07-07 21:44
 */
@Data
@ToString
@Builder
public class FilTransfer {

    private String from;
    private String to;
    private BigMoney amount;


}
