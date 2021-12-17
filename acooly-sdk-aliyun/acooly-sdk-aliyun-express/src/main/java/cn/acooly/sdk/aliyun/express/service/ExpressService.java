/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-14 10:21
 */
package cn.acooly.sdk.aliyun.express.service;

import cn.acooly.sdk.aliyun.express.dto.ExpressTrackInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * @author zhangpu
 * @date 2021-10-14 10:21
 */
public interface ExpressService {


    /**
     * 查询信息及快递轨迹
     * （推荐方法）
     *
     * @param orderNo      物流单号
     * @param expCompCode  物流公司编码 可参考常用编码`CommonExpComp`,如果常用编码中没有，则可不传。
     * @param phonePostfix 寄/收人手机后4位（顺丰必填）
     * @return
     * @see cn.acooly.sdk.aliyun.express.enums.CommonExpComp
     */
    ExpressTrackInfo track(@NotBlank String orderNo, String expCompCode, @Null String phonePostfix);

    /**
     * @param orderNo
     * @param expCompCode
     * @return
     */
    default ExpressTrackInfo track(@NotBlank String orderNo, String expCompCode) {
        return track(orderNo, expCompCode, null);
    }

    /**
     * 是在没有办法采是否用
     * 成本翻倍，需要先通过单号查物流公司，然后再查轨迹
     *
     * @param orderNo
     * @return
     */
    default ExpressTrackInfo track(@NotBlank String orderNo) {
        return track(orderNo, null, null);
    }

}
