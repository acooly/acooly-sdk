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

    // 未完待继续... https://market.aliyun.com/products/57126001/cmapi00054243.html?spm=5176.730005.result.1.4c913524ABlUgX&innerSource=search_%E5%BF%AB%E9%80%92%E7%89%A9%E6%B5%81%E6%9F%A5%E8%AF%A2#sku=yuncode4824300001
    // todo: 待处理为完成的状态
    ;

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
