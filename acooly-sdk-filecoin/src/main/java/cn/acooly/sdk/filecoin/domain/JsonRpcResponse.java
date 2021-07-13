/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-16 17:18
 */
package cn.acooly.sdk.filecoin.domain;

import com.acooly.core.utils.Strings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * JSON-RPC协议 响应报文
 *
 * @author zhangpu
 * @date 2021-06-16 17:18
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class JsonRpcResponse<T> {

    /**
     * 指定JSON-RPC协议版本的字符串，必须准确写为“2.0”
     */
    private String jsonrpc = "2.0";

    /**
     * 成功响应报文：该成员在成功时必须包含的结果
     * 当调用方法引起错误时必须不包含该成员
     * 服务端中的被调用方法决定了该成员的值。
     */
    private String result;

    /**
     * result解析转换JSON的对象
     */
    private T resultObject;

    /**
     * 该成员在失败是必须包含。
     * 当没有引起错误的时必须不包含该成员。
     * 该成员参数值必须为对象:
     * {
     * code: "使用数值表示该异常的错误类型。 必须为整数。",
     * message: "对该错误的简单描述字符串。 该描述应尽量限定在简短的一句话。",
     * data: "包含关于错误附加信息的基本类型或结构化类型"
     * }
     * <p>
     * 保留错误码：
     * <li>-32700: Parse error语法解析错误</li>
     * <li>-32600: Invalid Request无效请求 : 发送的json不是一个有效的请求对象。</li>
     * <li>-32601: Method not found找不到方法</li>
     * <li>-32602: Invalid params无效的参数</li>
     * <li>-32603: Internal error内部错误</li>
     * <li>-32000 to -32099: Server error服务端错误 : 预留用于自定义的服务器错误。</li>
     */
    private String error;

    /**
     * error解析对象
     */
    private JsonRpcError errorObject;

    /**
     * 已建立客户端的唯一标识id
     * 如果不包含该成员则被认定为是一个通知。该值一般不为NULL，若为数值则不应该包含小数。
     */
    private String id;

    public boolean isSuccess() {
        return Strings.isBlank(this.error);
    }

    public void convertAndSetResult(Object object) {
        this.setResultObject((T) object);
    }

}
