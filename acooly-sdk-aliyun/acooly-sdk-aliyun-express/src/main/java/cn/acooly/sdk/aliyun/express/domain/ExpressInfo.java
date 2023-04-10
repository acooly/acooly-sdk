/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-07 15:36
 */
package cn.acooly.sdk.aliyun.express.domain;

import com.acooly.core.common.facade.InfoBase;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 快递信息
 *
 * @author zhangpu
 * @date 2023-04-07 15:36
 */
@Getter
@Setter
public class ExpressInfo extends InfoBase {

    /**
     * 运单号
     */
    private String mailNo;

    /**
     * 最新更新时间
     */
    private Date lastTime;

    /**
     * 最新消息
     */
    private String lastMessage;

    /**
     * 从揽收到送达所耗时间
     */
    private String taskTime;

    /**
     * 最新状态
     * 物流流转状态
     */
    private String lastStatus;

    /**
     * 最新状态描述
     */
    private String lastStatusDesc;

    /**
     * 快递公司信息
     */
    private ExpressCompany expressCompany;

    /**
     * 运单轨迹
     */
    private List<ExpressTrack> expressTracks;

    /**
     * 根据状态分组的运单轨迹
     */
    private Map<String, Set<ExpressTrack>> expressTrackMap;

}
