/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-13 23:24
 */
package cn.acooly.sdk.aliyun.common.transport;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-10-13 23:24
 */
@Slf4j
@Data
public class AliyunRequest implements Serializable {

    /**
     * 请求地址
     */
    private String url;

    /**
     * http头
     */
    private Map<String, String> headers = new HashMap();

    /**
     * 参数
     */
    private Map<String, String> params = new HashMap();

    /**
     * 请求体
     */
    private String body;
}
