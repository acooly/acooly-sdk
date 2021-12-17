/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-14 10:37
 */
package cn.acooly.sdk.aliyun.express.dto;

import com.acooly.core.common.facade.InfoBase;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangpu
 * @date 2021-10-14 10:37
 */
@Slf4j
@Data
public class ExpressTrackData implements Serializable {

    /**
     * 快递跟踪信息
     */
    private String context;

    /**
     * 快递跟踪时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;
}
