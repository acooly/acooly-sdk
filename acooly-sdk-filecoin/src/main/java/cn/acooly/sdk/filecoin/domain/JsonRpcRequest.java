/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-16 17:15
 */
package cn.acooly.sdk.filecoin.domain;

import com.google.common.collect.Lists;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * JSON-RPC协议 请求报文
 *
 * @author zhangpu
 * @date 2021-06-16 17:15
 */
@Getter
@Setter
@ToString
public class JsonRpcRequest {

    /**
     * 指定JSON-RPC协议版本的字符串，必须准确写为“2.0”
     */
    private String jsonrpc = "2.0";

    /**
     * 包含所要调用方法名称的字符串，以rpc开头的方法名，用英文句号（U+002E or ASCII 46）
     * 连接的为预留给rpc内部的方法名及扩展名，且不能在其他地方使用
     */
    private String method;

    /**
     * 参数集合
     */
    private List<Object> params = Lists.newArrayList();

    /**
     * 已建立客户端的唯一标识id
     * 如果不包含该成员则被认定为是一个通知。该值一般不为NULL，若为数值则不应该包含小数。
     */
    private Long id;

    public void addParam(Object param) {
        this.params.add(param);
    }

    public JsonRpcRequest() {
    }

    public JsonRpcRequest(String method) {
        this.method = method;
    }

    public JsonRpcRequest(String method, List<Object> params) {
        this.method = method;
        this.params = params;
    }
}
