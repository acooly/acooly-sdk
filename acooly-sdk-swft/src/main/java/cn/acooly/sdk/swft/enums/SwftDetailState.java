/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-20 10:43
 */
package cn.acooly.sdk.swft.enums;
/**
 * SWFT订单状态
 * <p>
 * (1)wait_deposit_send:等待存币发送
 * (2)timeout:超时；
 * (3)wait_exchange_push:等待交换信息推送；
 * (4)wait_exchange_return:等待交换信息返回；
 * (5.1)wait_receive_send:等待接收币种发送, wait_receive_confirm:等待接收币种确认, receive_complete:接收币种确认完成.
 * (5.2)wait_refund_send:等待退原币币种发送, wait_refund_confirm:等待退原币币种确认, refund_complete:退原币币种确认完成；
 * (6)ERROR/error:正在处理的订单
 * (7)WAIT_KYC: 等待进行KYC或联系客服提供链接
 *
 * @author zhangpu
 * @date 2021-12-20 10:43
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum SwftDetailState implements Messageable {
    wait_deposit_send("wait_deposit_send", "等待存币发送"),
    timeout("timeout", "超时"),
    wait_exchange_push("wait_exchange_push", "等待交换信息推送"),
    wait_exchange_return("wait_exchange_return", "等待交换信息返回"),
    wait_receive_send("wait_receive_send", "等待接收币种发送"),
    wait_receive_confirm("wait_receive_confirm", "等待接收币种确认"),
    receive_complete("receive_complete", "接收币种确认完成"),
    wait_refund_send("wait_refund_send", "等待退原币币种发送"),
    wait_refund_confirm("wait_refund_confirm", "等待退原币币种确认"),
    refund_complete("refund_complete", "退原币币种确认完成"),
    error("error", "正在处理的订单"),
    WAIT_KYC("WAIT_KYC", "等待进行KYC或联系客服提供链接");

    private final String code;
    private final String message;

    SwftDetailState(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (SwftDetailState type : values()) {
            map.put(type.getCode(), type.getMessage());
        }
        return map;
    }

    /**
     * 通过枚举值码查找枚举值。
     *
     * @param code 查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException 如果 code 没有对应的 Status 。
     */
    public static SwftDetailState find(String code) {
        for (SwftDetailState status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<SwftDetailState> getAll() {
        List<SwftDetailState> list = new ArrayList<SwftDetailState>();
        for (SwftDetailState status : values()) {
            list.add(status);
        }
        return list;
    }

    /**
     * 获取全部枚举值码。
     *
     * @return 全部枚举值码。
     */
    public static List<String> getAllCode() {
        List<String> list = new ArrayList<String>();
        for (SwftDetailState status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
