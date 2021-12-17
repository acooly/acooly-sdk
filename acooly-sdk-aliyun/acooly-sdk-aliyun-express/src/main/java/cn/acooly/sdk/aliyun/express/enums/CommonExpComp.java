/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-14 11:06
 */
package cn.acooly.sdk.aliyun.express.enums;
/**
 * @author zhangpu
 * @date 2021-10-14 11:06
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum CommonExpComp implements Messageable {

    shunfeng("shunfeng", "顺丰", "http://static.showapi.com/app2/img/expImg/shunfeng.jpg"),
    shentong("shentong", "申通快递", "http://static.showapi.com/app2/img/expImg/shentong.jpg"),
    yuantong("yuantong", "圆通速递", "http://static.showapi.com/app2/img/expImg/yuantong.jpg"),
    yunda("yunda", "韵达速递", "http://static.showapi.com/app2/img/expImg/yunda.jpg"),
    ydky("ydky", "韵达快运", ""),
    zhongtong("zhongtong", "中通快递", "http://static.showapi.com/app2/img/expImg/zto.jpg"),
    ztoKy("ztoKy", "中通快运", ""),
    jt("jt", "极兔速递", "http://www.jtexpress.com.cn/images/footer_logo.png"),
    jingdong("jingdong", "京东快递", ""),
    bgpyghx("bgpyghx", "中国邮政包裹、平邮、挂号信", ""),
    intmail("intmail", "中国邮政国际包裹", ""),
    emsen("emsen", "EMS", "");

    private final String code;
    private final String message;
    private final String logo;

    CommonExpComp(String code, String message, String logo) {
        this.code = code;
        this.message = message;
        this.logo = logo;
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
        for (CommonExpComp type : values()) {
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
    public static CommonExpComp find(String code) {
        for (CommonExpComp status : values()) {
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
    public static List<CommonExpComp> getAll() {
        List<CommonExpComp> list = new ArrayList<CommonExpComp>();
        for (CommonExpComp status : values()) {
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
        for (CommonExpComp status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
