/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-16 17:30
 */
package cn.acooly.sdk.filecoin.transport;

import cn.acooly.sdk.filecoin.FilecoinSdkProperties;
import cn.acooly.sdk.filecoin.domain.JsonRpcError;
import cn.acooly.sdk.filecoin.domain.JsonRpcRequest;
import cn.acooly.sdk.filecoin.domain.JsonRpcResponse;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Reflections;
import com.acooly.core.utils.Strings;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * JSON-RPC Http实现
 *
 * @author zhangpu
 * @date 2021-06-16 17:30
 */
@Slf4j
@Component
public class JsonRpcHttpTransport implements JsonRpcTransport {

    @Autowired
    private FilecoinSdkProperties filecoinSdkProperties;

    public JsonRpcHttpTransport() {
    }

    public JsonRpcHttpTransport(FilecoinSdkProperties filecoinSdkProperties) {
        this.filecoinSdkProperties = filecoinSdkProperties;
    }

    @Override
    public <T extends JsonRpcResponse> T send(JsonRpcRequest request, Class<T> responseClass) {
        FilecoinSdkProperties.Gateway g = filecoinSdkProperties.getGateway();
        prevHandleRequest(request);
        String requestBody = toJson(request);
        log.info("Requ-Body {} : {}",request.getMethod(), requestBody);
        String responseBody = null;
        try {
            HttpRequest httpRequest = HttpRequest.post(g.getUrl())
                    .contentType(HttpRequest.CONTENT_TYPE_JSON)
                    .authorization("Bearer " + g.getToken())
                    .connectTimeout(g.getConnTimeout() * 1000)
                    .readTimeout(g.getReadTimeout() * 1000)
                    .send(toJson(request));
            responseBody = httpRequest.body();
            log.info("Resp-Body {} : {}", request.getMethod(),Strings.trimToEmpty(responseBody));
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.COMMUNICATION_ERROR, e);
        }
        return (T) fromJson(responseBody, responseClass);
    }

    protected void prevHandleRequest(JsonRpcRequest request) {
        // 初始化随机ID
        if (request.getId() == null) {
            request.setId(RandomUtils.nextLong());
        }
    }

    protected String toJson(JsonRpcRequest request) {
        return JSON.toJSONString(request);
    }

    protected JsonRpcResponse fromJson(String json, Class clazz) {
        JsonRpcResponse jsonRpcResponse = (JsonRpcResponse) Reflections.createObject(clazz);
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject error = jsonObject.getJSONObject("error");
        if (error != null) {
            jsonRpcResponse.setError(error.toJSONString());
            jsonRpcResponse.setErrorObject(error.toJavaObject(JsonRpcError.class));
        }
        Object resultObject = jsonObject.get("result");
        if (resultObject != null) {
            if (JSONObject.class.isAssignableFrom(resultObject.getClass())
                    || JSONArray.class.isAssignableFrom(resultObject.getClass())) {
                JSON result = (JSON) resultObject;
                jsonRpcResponse.setResult(result.toJSONString());
                Class resultClass = getGenericClass(clazz, 0);
                if (resultClass != null) {
                    jsonRpcResponse.convertAndSetResult(result.toJavaObject(resultClass));
                }
            } else {
                jsonRpcResponse.setResult(String.valueOf(resultObject));
                jsonRpcResponse.setResultObject(resultObject);
            }
        }
        jsonRpcResponse.setId(jsonObject.getString("id"));
        jsonRpcResponse.setJsonrpc(jsonObject.getString("jsonrpc"));
        return jsonRpcResponse;
    }


    public static Class getGenericClass(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

            if ((params == null) || (params.length < (index - 1))) {
                return null;
            }

            if (params[index] instanceof ParameterizedTypeImpl) {
                ParameterizedTypeImpl ptl = (ParameterizedTypeImpl) params[index];
                return ptl.getRawType();
            } else {
                return (Class) params[index];
            }

        }
        return null;
    }

}
