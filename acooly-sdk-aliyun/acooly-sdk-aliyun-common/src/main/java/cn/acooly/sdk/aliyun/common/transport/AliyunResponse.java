/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-13 23:27
 */
package cn.acooly.sdk.aliyun.common.transport;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-10-13 23:27
 */
@Slf4j
@Data
public class AliyunResponse implements Serializable {

    /**
     * 响应http头
     */
    private Map<String, String> headers = new HashMap();

    /**
     * 响应体
     */
    private String body;

}
