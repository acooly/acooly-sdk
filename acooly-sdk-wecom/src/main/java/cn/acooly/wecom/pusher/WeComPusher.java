/**
 * acooly-pm
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2022-02-21 13:09
 */
package cn.acooly.wecom.pusher;

import cn.acooly.wecom.pusher.message.TextWeComMsg;
import cn.acooly.wecom.pusher.message.WeComMsg;
import cn.acooly.wecom.pusher.message.WeComResult;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Strings;
import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 企业微信 消息推送
 *
 * @author zhangpu
 * @date 2022-02-21 13:09
 */
@Slf4j
public class WeComPusher {

    public static void pushText(String mobileNo, String content, String webHookAddress) {
        push(new TextWeComMsg(content, mobileNo), webHookAddress);
    }

    public static void pushText(List<String> mobileNos, String content, String webHookAddress) {
        push(new TextWeComMsg(content, mobileNos), webHookAddress);
    }

    /**
     * 推送企业微信消息
     *
     * @param weComMsg
     */
    public static void push(WeComMsg weComMsg, String webHookAddress) {
        if (Strings.isBlank(webHookAddress)) {
            return;
        }
        try {
            String jsonMsg = weComMsg.toJson();
            log.info("WeCom request: {}", jsonMsg);
            HttpRequest httpRequest = HttpRequest.post(webHookAddress)
                    .contentType("application/json", "utf-8")
                    .send(weComMsg.toJson());
            String responseBody = httpRequest.body();
            log.info("WeCom response: {}", responseBody);
            WeComResult result = JSON.parseObject(responseBody, WeComResult.class);
            if (result != null && result.getErrorcode() != WeComResult.SUCCESS_CODE) {
                log.warn("WeCom pusher Error: {}/{}", result.getErrorcode(), result.getErrmsg());
                throw new BusinessException("WeCom_Error_Code_" + result.getErrorcode(), result.getErrmsg(), "");
            }
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            log.warn("WeCom pusher 内部错误: {}", e.getMessage());
            throw new BusinessException(CommonErrorCodes.COMMUNICATION_ERROR, e.getMessage());
        }
    }


}
