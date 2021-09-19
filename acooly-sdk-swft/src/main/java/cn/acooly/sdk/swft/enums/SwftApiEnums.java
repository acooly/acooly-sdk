/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 23:08
 */
package cn.acooly.sdk.swft.enums;
/**
 * @author zhangpu
 * @date 2021-09-17 23:08
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum SwftApiEnums implements Messageable {

    QueryCoinList("QueryCoinList", "/api/v1/queryCoinList", "查询币种列表接口"),
    GetBaseInfo("GetBaseInfo", "/api/v1/getBaseInfo", "获取汇率接口"),
    GetInfo("GetInfo", "/api/v1/getInfo", "批量获取汇率接口"),
    AccountExchange("AccountExchange", "/api/v2/accountExchange", "闪兑接口"),
    QueryAllTrade("QueryAllTrade", "/api/v2/queryAllTrade", "查询兑换记录接口"),
    QueryOrderState("QueryOrderState", "/api/v2/queryOrderState", "查询订单状态接口"),
    ;

    private final String code;
    private final String service;
    private final String message;

    SwftApiEnums(String code, String service, String message) {
        this.code = code;
        this.message = message;
        this.service = service;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getService() {
        return service;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    public String service() {
        return service;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (SwftApiEnums type : values()) {
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
    public static SwftApiEnums find(String code) {
        for (SwftApiEnums status : values()) {
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
    public static List<SwftApiEnums> getAll() {
        List<SwftApiEnums> list = new ArrayList<SwftApiEnums>();
        for (SwftApiEnums status : values()) {
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
        for (SwftApiEnums status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
