/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-14 10:28
 */
package cn.acooly.sdk.aliyun.express.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangpu
 * @date 2021-10-14 10:28
 */
@Slf4j
@Data
public class ExpressTrackInfo implements Serializable {

    /**
     * 返回结果编码
     * 0 查询成功 或 提交成功。 1 输入参数错误。 2 查不到物流信息。 3 单号不符合规则。 4 快递公司编码不符合规则。 5 快递查询渠道异常。 6 auto时未查到单号对应的快递公司,请指定快递公司编码。 7 单号与手机号不匹配 其他参数：接口调用失败
     */
    @JsonProperty(value = "ret_code")
    private int resultCode;

    /**
     * 返回状态名称
     */
    private String resultText;

    /**
     * 快递公司编码
     * 例如：zhongtong
     */
    @JsonProperty(value = "expSpellName")
    private String expCompCode;
    /**
     * 快递公司名称
     * 例如：中通快递
     */
    @JsonProperty(value = "expTextName")
    private String expCompName;

    /**
     * 快递公司LOGO
     */
    @JsonProperty("logo")
    private String expCompLogo;

    /**
     * 快递公司电话
     */
    @JsonProperty(value = "tel")
    private String expCompTel;

    /**
     * 快递单号
     */
    @JsonProperty(value = "mailNo")
    private String orderNo;

    /**
     * 快递状态
     * 1 暂无记录 2 在途中 3 派送中 4 已签收 (完结状态) 5 用户拒签 6 疑难件 7 无效单 (完结状态) 8 超时单 9 签收失败 10 退回
     */
    private int status;

    private String statusText;

    /**
     * 是否查询成功
     * true：查询成功，表示ret_code=0且data的长度>0
     */
    @JsonProperty(value = "flag")
    private Boolean success;

    /**
     * 跟踪信息
     */
    @JsonProperty("data")
    private List<ExpressTrackData> data;


}
