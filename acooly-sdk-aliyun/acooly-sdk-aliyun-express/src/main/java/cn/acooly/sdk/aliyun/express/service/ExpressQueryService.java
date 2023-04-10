/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-09 16:24
 */
package cn.acooly.sdk.aliyun.express.service;

import cn.acooly.sdk.aliyun.express.domain.ExpressInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * 快递查询服务
 * <p>
 * 通用版本；同时查询快递寄出信息，快递公司信息和轨迹信息；支持轨迹的查询和状态分类
 *
 * @author zhangpu
 * @date 2023-04-09 16:24
 */
public interface ExpressQueryService {

    /**
     * 查询快递信息
     *
     * @param mailNo             快递单号
     * @param expressCompanyCode 快递公司编码
     * @param mobileNo           收件人手机号
     * @return
     */
    ExpressInfo query(@NotBlank String mailNo, @Null String expressCompanyCode, @Null String mobileNo);

    /**
     * 顺丰查询（需要提供收或寄的手机号码）
     *
     * @param mailNo
     * @param mobileNo
     * @return
     */
    default ExpressInfo query(@NotBlank String mailNo, @Null String mobileNo) {
        return query(mailNo, null, mobileNo);
    }

    /**
     * 查询（推荐）
     *
     * @param mailNo
     * @return
     */
    default ExpressInfo query(@NotBlank String mailNo) {
        return query(mailNo, null, null);
    }
}
