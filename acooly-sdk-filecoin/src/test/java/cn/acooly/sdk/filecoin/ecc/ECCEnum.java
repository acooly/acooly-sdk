/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-07-26 12:04
 */
package cn.acooly.sdk.filecoin.ecc;
/**
 * @author zhangpu
 * @date 2021-07-26 12:04
 */

public enum ECCEnum {
    ALGORITHM("EC"),
    PROVIDER("BC"),
    PUBLIC_KEY("PUBLIC_KEY"),
    PRIVATE_KEY("PRIVATE_KEY");


    private String value;

    ECCEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
