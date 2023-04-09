/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-09 15:39
 */
package cn.acooly.sdk.aliyun.express.domain;

import com.acooly.core.common.facade.InfoBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpu
 * @date 2023-04-09 15:39
 */
@Getter
@Setter
public class ExpressCompany extends InfoBase {

    /**
     * 快递公司编码
     */
    private String code;

    /**
     * 快递公司名称
     */
    private String name;

    /**
     * 快递公司LOGO
     */
    private String logo;

    /**
     * 快递公司官网
     */
    private String url;

    /**
     * 快递公司电话
     */
    private String tel;
}
