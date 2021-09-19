/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-16 23:07
 */
package cn.acooly.sdk.swft.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangpu
 * @date 2021-09-16 23:07
 */
@Data
@ToString
public class SwftRequest {

    /**
     * 接口唯一标志
     * 例如：/api/v1/queryCoinList
     */
    @JsonIgnore
    private String service;

}
