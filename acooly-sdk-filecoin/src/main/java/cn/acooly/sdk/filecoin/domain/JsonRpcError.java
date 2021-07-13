/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-18 16:34
 */
package cn.acooly.sdk.filecoin.domain;

import com.acooly.core.utils.enums.Messageable;
import lombok.Data;

/**
 * @author zhangpu
 * @date 2021-06-18 16:34
 */
@Data
public class JsonRpcError implements Messageable {

    private String code;
    private String message;
    private String data;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
