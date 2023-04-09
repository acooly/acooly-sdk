/*
 * www.acooly.cn Inc.
 * Copyright (c) 2023 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2023-04-09 22:11 创建
 */
package cn.acooly.sdk.aliyun.express.dto.tianyan;

import com.acooly.core.common.facade.InfoBase;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangpu 2023-04-09 22:11
 */
@NoArgsConstructor
@Data
public class ExpTrack extends InfoBase {
    /**
     * areaCode : CN530100000000
     * areaName : 云南省,昆明市
     * subLogisticsStatus : ACCEPT
     * time : 1651840444000
     * logisticsStatus : ACCEPT
     * desc : 【昆明经开民办科技园】（952270、15711111111） 的 匡光明（15522222222） 已揽收
     */

    private String areaCode;
    private String areaName;
    private String subLogisticsStatus;
    private long time;
    private String logisticsStatus;
    private String desc;
}
