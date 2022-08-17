/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-27 23:39
 */
package cn.acooly.sdk.exchangerate.enums;
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

public enum LegalCurrency implements Messageable {
    /**
     * 人民币
     */
    CNY("CNY", "人民币", "￥", 156, 2),
    USD("USD", "美元", "$", 840, 2),
    EUR("EUR", "欧元", "€", 978, 2),
    GBP("GBP", "英镑", "￡", 826, 2),
    HKD("HKD", "港元", "", 344, 2),
    MOP("MOP", "澳门元", "", 446, 2),
    TWD("TWD", "新台币", "", 901, 2),
    NZD("NZD", "新西兰元", "", 554, 2),
    JPY("JPY", "日元", "¥", 392, 0),
    KRW("KRW", "韩元", "₩", 410, 0),
    AUD("AUD", "澳元", "", 36, 2),
    CAD("CAD", "加元", "", 124, 2),
    TRY("TRY", "土耳其里拉", "", 949, 2),
    PLN("PLN", "波兰兹罗提", "", 985, 2),
    HUF("HUF", "匈牙利福林", "", 348, 2),
    DKK("DKK", "丹麦克朗", "", 208, 2),
    MAD("MAD", "摩洛哥迪拉姆", "", 504, 2),
    NOK("NOK", "挪威克朗", "", 578, 2),
    CZK("CZK", "捷克克朗", "", 203, 2),
    CHF("CHF", "瑞士法郎", "", 756, 2),
    SAR("SAR", "沙特里亚尔", "", 682, 2),
    ;

    private final String code;
    private final String message;
    private final String symbol;
    private final int numericCode;
    private final int scale;

    LegalCurrency(String code, String message, String symbol, int numericCode, int scale) {
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
        for (LegalCurrency type : values()) {
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
    public static LegalCurrency find(String code) {
        for (LegalCurrency status : values()) {
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
    public static List<LegalCurrency> getAll() {
        List<LegalCurrency> list = new ArrayList<LegalCurrency>();
        for (LegalCurrency status : values()) {
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
        for (LegalCurrency status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
