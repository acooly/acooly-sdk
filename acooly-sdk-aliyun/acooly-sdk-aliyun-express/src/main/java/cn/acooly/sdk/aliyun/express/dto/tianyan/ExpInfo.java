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

import java.util.List;

/**
 * @author zhangpu 2023-04-09 22:11
 */
@NoArgsConstructor
@Data
public class ExpInfo extends InfoBase {
    /**
     * cpCode : ZTO
     * theLastTime : 2022-05-09 22:45:05
     * mailNo : 75876648253690
     * theLastMessage : 您的快递已签收, 签收人在【多多买菜的xxx幢一单元】(xxx一单元)领取。如有疑问请电联:（13711112222）, 投诉电话:（13711112222）, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】
     * cpMobile : 95311
     * logisticsCompanyName : 中通快递
     * cpUrl : https://www.zto.com/
     * takeTime : 2天21小时45分
     * logisticsStatusDesc : 已签收
     * logisticsTraceDetailList : [{"areaCode":"CN530100000000","areaName":"云南省,昆明市","subLogisticsStatus":"ACCEPT","time":1651840444000,"logisticsStatus":"ACCEPT","desc":"【昆明经开民办科技园】（952270、15711111111） 的 匡光明（15522222222） 已揽收"}]
     * logisticsStatus : SIGN
     */

    private String cpCode;
    private String theLastTime;
    private String mailNo;
    private String theLastMessage;
    private String cpMobile;
    private String logisticsCompanyName;
    private String cpUrl;
    private String takeTime;
    private String logisticsStatusDesc;
    private String logisticsStatus;
    private List<ExpTrack> logisticsTraceDetailList;
}
