/**
 * acooly-pm
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2022-02-21 15:33
 */
package cn.acooly.wecom.pusher.message;

import com.acooly.core.common.facade.InfoBase;
import lombok.Data;

/**
 * @author zhangpu
 * @date 2022-02-21 15:33
 */
@Data
public class WeComResult extends InfoBase {

    public static final int SUCCESS_CODE = 0;

    private int errorcode;
    private String errmsg;
}
