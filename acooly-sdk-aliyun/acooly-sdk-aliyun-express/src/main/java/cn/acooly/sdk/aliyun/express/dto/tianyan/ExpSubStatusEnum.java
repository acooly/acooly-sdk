/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-10 00:04
 */
package cn.acooly.sdk.aliyun.express.dto.tianyan;
/**
 * @author zhangpu
 * @date 2023-04-10 00:04
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum ExpSubStatusEnum implements Messageable {

    RECEIVE("RECEIVE", "接单中", ExpStatusEnum.WAIT_ACCEPT),
    WAIT_ACCEPT("WAIT_ACCEPT", "待揽收", ExpStatusEnum.WAIT_ACCEPT),
    ACCEPT("ACCEPT", "已揽收", ExpStatusEnum.ACCEPT),

    TRANSPORT("TRANSPORT", "运输中", ExpStatusEnum.TRANSPORT),
    SEND_ON("SEND_ON", "转单或修改地址转寄", ExpStatusEnum.TRANSPORT),
    ARRIVE_CITY("ARRIVE_CITY", "到达目的城市", ExpStatusEnum.TRANSPORT),

    DELIVERING("DELIVERING", "派件中", ExpStatusEnum.DELIVERING),
    STA_INBOUND("STA_INBOUND", "已放入快递柜或驿站", ExpStatusEnum.DELIVERING),

    AGENT_SIGN("AGENT_SIGN", "已代签收", ExpStatusEnum.AGENT_SIGN),

    SIGN("SIGN", "已签收", ExpStatusEnum.SIGN),
    STA_SIGN("STA_SIGN", "从快递柜或驿站取出", ExpStatusEnum.SIGN),
    RETURN_SIGN("RETURN_SIGN", "退回签收", ExpStatusEnum.SIGN),

    FAILED("FAILED", "包裹异常", ExpStatusEnum.FAILED),
    REFUSE_SIGN("REFUSE_SIGN", "拒收", ExpStatusEnum.FAILED),
    DELIVER_ABNORMAL("DELIVER_ABNORMAL", "派件异常", ExpStatusEnum.FAILED),
    RETENTION("RETENTION", "滞留件", ExpStatusEnum.FAILED),
    ISSUE("ISSUE", "问题件", ExpStatusEnum.FAILED),
    RETURN("RETURN", "退回件", ExpStatusEnum.FAILED),
    DAMAGE("DAMAGE", "破损", ExpStatusEnum.FAILED),
    CANCEL_ORDER("CANCEL_ORDER", "揽件取消", ExpStatusEnum.FAILED);

    private final String code;
    private final String message;
    private final ExpStatusEnum mainStatus;

    ExpSubStatusEnum(String code, String message, ExpStatusEnum mainStatus) {
        this.code = code;
        this.message = message;
        this.mainStatus = mainStatus;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (ExpSubStatusEnum type : values()) {
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
    public static ExpSubStatusEnum find(String code) {
        for (ExpSubStatusEnum status : values()) {
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
    public static List<ExpSubStatusEnum> getAll() {
        List<ExpSubStatusEnum> list = new ArrayList<ExpSubStatusEnum>();
        for (ExpSubStatusEnum status : values()) {
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
        for (ExpSubStatusEnum status : values()) {
            list.add(status.code());
        }
        return list;
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

}
