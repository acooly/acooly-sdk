/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-14 14:05
 */
package cn.acooly.sdk.aliyun.express;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-10-14 14:05
 */
@Slf4j
public class ExpressConstants {

    /**
     * 查询结果常量
     * 0 查询成功 或 提交成功。 1 输入参数错误。 2 查不到物流信息。 3 单号不符合规则。 4 快递公司编码不符合规则。
     * 5 快递查询渠道异常。 6 auto时未查到单号对应的快递公司,请指定快递公司编码。 7 单号与手机号不匹配 其他参数：接口调用失败
     */
    public static final Map<Integer, String> RESULT_CODE_MAPPING = Maps.newLinkedHashMap();

    static {
        RESULT_CODE_MAPPING.put(0, "查询成功");
        RESULT_CODE_MAPPING.put(1, "输入参数错误");
        RESULT_CODE_MAPPING.put(2, "查不到物流信息");
        RESULT_CODE_MAPPING.put(3, "单号不符合规则");
        RESULT_CODE_MAPPING.put(4, "快递公司编码不符合规则");
        RESULT_CODE_MAPPING.put(5, "快递查询渠道异常");
        RESULT_CODE_MAPPING.put(6, "auto时未查到单号对应的快递公司,请指定快递公司编码");
        RESULT_CODE_MAPPING.put(7, "单号与手机号不匹配");
    }

    /**
     * 快递状态
     * 1 暂无记录 2 在途中 3 派送中 4 已签收 (完结状态) 5 用户拒签 6 疑难件
     * 7 无效单 (完结状态) 8 超时单 9 签收失败 10 退回
     */
    public static final Map<Integer, String> EXPRESS_STATUS_MAPPING = Maps.newLinkedHashMap();

    static {
        EXPRESS_STATUS_MAPPING.put(1, "暂无记录");
        EXPRESS_STATUS_MAPPING.put(2, "在途中");
        EXPRESS_STATUS_MAPPING.put(3, "派送中");
        EXPRESS_STATUS_MAPPING.put(4, "已签收");
        EXPRESS_STATUS_MAPPING.put(5, "用户拒签");
        EXPRESS_STATUS_MAPPING.put(6, "疑难件");
        EXPRESS_STATUS_MAPPING.put(7, "无效单");
        EXPRESS_STATUS_MAPPING.put(8, "超时单");
        EXPRESS_STATUS_MAPPING.put(9, "签收失败");
        EXPRESS_STATUS_MAPPING.put(10, "退回");
    }

    public static String getStatusText(Integer status) {
        return EXPRESS_STATUS_MAPPING.get(status);
    }

    public static String getResultText(Integer resultCode) {
        return RESULT_CODE_MAPPING.get(resultCode);
    }

}
