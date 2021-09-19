/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-16 23:07
 */
package cn.acooly.sdk.swft.message;

import cn.acooly.sdk.swft.SwftProperties;
import com.acooly.core.utils.Strings;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangpu
 * @date 2021-09-16 23:07
 */
@Data
public class SwftResponse {

    private String resCode;
    private String resMsg;

    public boolean success() {
        return Strings.equalsIgnoreCase(resCode, SwftProperties.SUCCESS_CODE);
    }

}
