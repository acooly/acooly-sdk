/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-16 17:30
 */
package cn.acooly.sdk.openai.support;

import cn.acooly.sdk.openai.OpenAiSdkProperties;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Strings;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * JSON-RPC Http实现
 *
 * @author zhangpu
 * @date 2023-05-16 17:30
 */
@Slf4j
@NoArgsConstructor
@Component
public class JsonHttpTransport {

    @Autowired
    private OpenAiSdkProperties openAiSdkProperties;

    public JsonHttpTransport(OpenAiSdkProperties openAiSdkProperties) {
        this.openAiSdkProperties = openAiSdkProperties;
    }

    public String post(String url, String body) {
        String endPointUrl = Strings.isHttpUrl(url) ? url : this.getGateway().getUrl() + url;
        HttpRequest httpRequest = null;
        String responseBody = null;
        log.info("Requ POST {} : {}", url, body);
        try {
            if (getProxy().isEnable()) {
                responseBody = HttpRequest.post(endPointUrl)
                        .useProxy(getProxy().getHost(), getProxy().getPort())
                        .contentType(HttpRequest.CONTENT_TYPE_JSON)
                        .authorization("Bearer " + this.getGateway().getKey())
                        .connectTimeout(this.getGateway().getConnTimeout() * 1000)
                        .readTimeout(this.getGateway().getReadTimeout() * 1000)
                        .send(body).body();
            } else {
                responseBody = HttpRequest.post(endPointUrl)
                        .contentType(HttpRequest.CONTENT_TYPE_JSON)
                        .authorization("Bearer " + this.getGateway().getKey())
                        .connectTimeout(this.getGateway().getConnTimeout() * 1000)
                        .readTimeout(this.getGateway().getReadTimeout() * 1000)
                        .send(body).body();
            }
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.COMMUNICATION_ERROR, e);
        }
        log.info("Resp body : {}", Strings.trimToEmpty(responseBody));
        return responseBody;
    }

    public String get(String url) {
        String endPointUrl = Strings.isHttpUrl(url) ? url : this.getGateway().getUrl() + url;
        log.info("Requ GET {}", url);
        String responseBody = null;
        try {
            if (getProxy().isEnable()) {
                responseBody = HttpRequest.get(endPointUrl)
                        .useProxy(getProxy().getHost(), getProxy().getPort())
                        .contentType(HttpRequest.CONTENT_TYPE_JSON)
                        .authorization("Bearer " + this.getGateway().getKey())
                        .connectTimeout(this.getGateway().getConnTimeout() * 1000)
                        .readTimeout(this.getGateway().getReadTimeout() * 1000)
                        .body();
            } else {
                responseBody = HttpRequest.get(endPointUrl)
                        .contentType(HttpRequest.CONTENT_TYPE_JSON)
                        .authorization("Bearer " + this.getGateway().getKey())
                        .connectTimeout(this.getGateway().getConnTimeout() * 1000)
                        .readTimeout(this.getGateway().getReadTimeout() * 1000)
                        .body();
            }
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.COMMUNICATION_ERROR, e);
        }
        log.info("Resp body: {}", Strings.trimToEmpty(responseBody));
        return responseBody;
    }


    protected OpenAiSdkProperties.Gateway getGateway() {
        return openAiSdkProperties.getGateway();
    }

    protected OpenAiSdkProperties.Proxy getProxy() {
        return openAiSdkProperties.getProxy();
    }

}
