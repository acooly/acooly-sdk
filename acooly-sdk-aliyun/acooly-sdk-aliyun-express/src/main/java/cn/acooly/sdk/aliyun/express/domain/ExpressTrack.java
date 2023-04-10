/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-09 16:00
 */
package cn.acooly.sdk.aliyun.express.domain;

import com.acooly.core.common.facade.InfoBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 运单轨迹
 *
 * @author zhangpu
 * @date 2023-04-09 16:00
 */
@Slf4j
@Getter
@Setter
@EqualsAndHashCode
public class ExpressTrack extends InfoBase {

    /**
     * 当前所在区域编码
     */
    private String areaCode;

    /**
     * 当前所在区域名称
     */
    private String areaName;

    /**
     * 物流流转状态
     */
    private String status;

    /**
     * 物流流转状态描述
     */
    private String statusText;

    /**
     * 物流流转子状态
     */
    private String subStatus;

    /**
     * 物流流转子状态描述
     */
    private String subStatusText;

    /**
     * 物流流转状态发生时间
     */
    private Date statusTime;

    /**
     * 物流流转描述
     */
    private String desc;

}
