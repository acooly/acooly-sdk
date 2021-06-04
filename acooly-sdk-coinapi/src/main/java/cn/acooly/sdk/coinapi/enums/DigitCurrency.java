/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-27 23:39
 */
package cn.acooly.sdk.coinapi.enums;
/**
 * 常用法币定义
 *
 * @author zhangpu
 * @date 2021-05-27 23:39
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum DigitCurrency implements Messageable {
    /**
     * 人民币
     */
    usdt("usdt", "泰达币", "T", 1, 8),
    btc("btc", "比特币", "B", 2, 8),
    eth("eth", "以太坊", "E", 3, 2),
    fil("fil", "文件币", "F", 4, 2);

    private final String code;
    private final String message;
    private final String symbol;
    private final int numericCode;
    private final int scale;

    DigitCurrency(String code, String message, String symbol, int numericCode, int scale) {
        this.code = code;
        this.message = message;
        this.symbol = symbol;
        this.numericCode = numericCode;
        this.scale = scale;
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
        for (DigitCurrency type : values()) {
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
    public static DigitCurrency find(String code) {
        for (DigitCurrency status : values()) {
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
    public static List<DigitCurrency> getAll() {
        List<DigitCurrency> list = new ArrayList<DigitCurrency>();
        for (DigitCurrency status : values()) {
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
        for (DigitCurrency status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
