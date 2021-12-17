/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-14 13:08
 */
package cn.acooly.sdk.aliyun.express.dto;

import com.acooly.core.utils.validate.Validators;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author zhangpu
 * @date 2021-10-14 13:08
 */
@Slf4j
@Data
public class ExpressTrackResult implements Serializable {

    @JsonProperty(value = "showapi_res_body")
    private ExpressTrackInfo expressTrackInfo;
}
