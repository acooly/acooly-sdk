/**
 * acooly-pm
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2022-02-21 13:41
 */
package cn.acooly.wecom.pusher.message;

import com.acooly.core.common.facade.InfoBase;
import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhangpu
 * @date 2022-02-21 13:41
 */
@Data
public class WeComMsg extends InfoBase {

    /**
     * 消息类型
     */
    @NotNull
    private WeComMsgType msgtype;

    /**
     * 消息报文
     *
     * @return
     */
    public String toJson() {
        return JSON.toJSONString(this);
    }


}
