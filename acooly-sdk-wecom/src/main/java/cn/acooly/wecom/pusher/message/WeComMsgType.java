/**
 * acooly-pm
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2022-02-21 13:44
 */
package cn.acooly.wecom.pusher.message;
/**
 * @author zhangpu
 * @date 2022-02-21 13:44
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum WeComMsgType implements Messageable {

    text("text", "文本"),
    markdown("markdown", "markdown"),
    image("image", "图片"),
    news("news", "图文"),
    file("file", "文件"),
    template_card("template_card", "模板");

    private final String code;
    private final String message;

    WeComMsgType(String code, String message) {
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
        for (WeComMsgType type : values()) {
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
    public static WeComMsgType find(String code) {
        for (WeComMsgType status : values()) {
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
    public static List<WeComMsgType> getAll() {
        List<WeComMsgType> list = new ArrayList<WeComMsgType>();
        for (WeComMsgType status : values()) {
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
        for (WeComMsgType status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
