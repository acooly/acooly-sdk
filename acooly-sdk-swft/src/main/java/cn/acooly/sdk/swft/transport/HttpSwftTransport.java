/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-16 17:30
 */
package cn.acooly.sdk.swft.transport;

import cn.acooly.sdk.swft.SwftProperties;
import cn.acooly.sdk.swft.message.SwftRequest;
import cn.acooly.sdk.swft.message.SwftResponse;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Strings;
import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Http实现
 *
 * @author zhangpu
 * @date 2021-06-16 17:30
 */
@Slf4j
@NoArgsConstructor
@Component
public class HttpSwftTransport implements SwftTransport {

    @Autowired(required = false)
    private SwftProperties swftProperties;

    @Override
    public <T extends SwftResponse> T send(SwftRequest request, Class<T> responseClass) {
        SwftProperties.Gateway g = swftProperties.getGateway();
        String url = g.getUrl() + request.getService();
        String requestBody = toJson(request);
        log.info("Requ {} : {}", url, requestBody);
        String responseBody = null;
        try {
            HttpRequest httpRequest = HttpRequest.post(url)
                    .contentType(HttpRequest.CONTENT_TYPE_JSON)
                    .connectTimeout(g.getConnTimeout() * 1000)
                    .readTimeout(g.getReadTimeout() * 1000)
                    .send(requestBody);
            responseBody = httpRequest.body();
            log.info("Resp {} : {}", url, Strings.trimToEmpty(responseBody));
            T response = fromJson(responseBody, responseClass);
            if (!response.success()) {
                throw new BusinessException(response.getResCode(), response.getResMsg(), "");
            }
            return response;
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.COMMUNICATION_ERROR, e);
        }
    }

    protected String toJson(SwftRequest request) {
        return JSON.toJSONString(request);
    }

    protected <T extends SwftResponse> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

//
//    protected <T extends SwftResponse> parseJson(String json, Class clazz) {
//        SwftResponse response = (SwftResponse) Reflections.createObject(clazz);
//        JSONObject jsonObject = JSON.parseObject(json);
//        Object resultObject = jsonObject.get("data");
//        if (resultObject != null) {
//            if (JSONObject.class.isAssignableFrom(resultObject.getClass())
//                    || JSONArray.class.isAssignableFrom(resultObject.getClass())) {
//                JSON result = (JSON) resultObject;
//                response.setResult(result.toJSONString());
//                Class resultClass = getGenericClass(clazz, 0);
//                if (resultClass != null) {
//                    response.convertAndSetResult(result.toJavaObject(resultClass));
//                }
//            } else {
//                response.setResult(String.valueOf(resultObject));
//                response.setResultObject(resultObject);
//            }
//        }
//        response.setResCode(jsonObject.getString("resCode"));
//        response.setResMsg(jsonObject.getString("resMsg"));
//        return response;
//    }


    public HttpSwftTransport(SwftProperties swftProperties) {
        this.swftProperties = swftProperties;
    }


}
