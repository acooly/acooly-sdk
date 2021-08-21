/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-26 11:24
 */
package cn.acooly.sdk.filecoin.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangpu
 * @date 2021-07-26 11:24
 */
@Slf4j
@ToString
@Data
@AllArgsConstructor
public class WalletAddress {

    private String address;

    private String privateKey;

}
