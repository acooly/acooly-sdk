/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-13 23:23
 */
package cn.acooly.sdk.aliyun.common.transport;

import cn.acooly.sdk.aliyun.common.AliyunProperties;
import cn.acooly.sdk.aliyun.common.config.AliyunMarketConfig;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Encodes;
import com.acooly.core.utils.Strings;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * http请求工具
 *
 * @author zhangpu
 * @date 2021-10-13 23:23
 */
@Slf4j
@Getter
@Setter
public class HttpTransport {

    @Autowired(required = false)
    private AliyunProperties aliyunProperties;

    private AliyunMarketConfig aliyunMarketConfig;

    /**
     * 默认网关地址
     */
    private String gateway;

    public HttpTransport(AliyunMarketConfig aliyunMarketConfig) {
        this.aliyunMarketConfig = aliyunMarketConfig;
        this.gateway = aliyunMarketConfig.getGateway();
    }

    public AliyunResponse request(AliyunRequest request) {

        HttpRequest httpRequest = HttpRequest.get(getUrl(request))
                .contentType(HttpRequest.CONTENT_TYPE_JSON)
                .readTimeout(aliyunMarketConfig.getReadTimeout())
                .connectTimeout(aliyunMarketConfig.getConnTimeout())
                .trustAllCerts();
        if (request.getHeaders() != null && request.getHeaders().size() > 0) {
            httpRequest.headers(request.getHeaders());
        }

//        httpRequest.header("Authorization","APPCODE:ac49b5284d0e4d6ebacd8675618947da");

        // 集成简单的AppCode认证。
        if (Strings.isNotBlank(getAppCode())) {
            httpRequest.authorization("APPCODE " + getAppCode());
        }

        if (Strings.isNotBlank(request.getBody())) {
            httpRequest.send(request.getBody());
        }

        if (httpRequest.code() != 200) {
            throw new BusinessException(CommonErrorCodes.COMMUNICATION_ERROR, "Http请求返回码:" + httpRequest.code());
        }

        AliyunResponse response = new AliyunResponse();
        response.setBody(httpRequest.body());

        Map<String, String> respHeaders = Maps.newHashMap();
        for (Map.Entry<String, List<String>> entry : httpRequest.headers().entrySet()) {
            if (Collections3.isEmpty(entry.getValue())) {
                continue;
            }
            String val = entry.getValue().size() == 1 ? Collections3.getFirst(entry.getValue()) :
                    Arrays.toString(entry.getValue().toArray());
            respHeaders.put(entry.getKey(), val);
        }
        response.setHeaders(respHeaders);
        return response;
    }

    private String getUrl(AliyunRequest request) {
        String url = request.getUrl();
        if (!Strings.isHttpUrl(url)) {
            url = getGateway() + url;
        }
        url = url + "?1=1";
        StringBuilder sb = new StringBuilder(url);
        for (Map.Entry<String, String> entry : request.getParams().entrySet()) {
            sb.append("&").append(entry.getKey()).append("=").append(Encodes.urlEncode(entry.getValue()));
        }
        return sb.toString();
    }

    private String getAppCode() {
        if (Strings.isNotBlank(aliyunMarketConfig.getAppCode())) {
            return aliyunMarketConfig.getAppCode();
        }

        if (aliyunProperties != null) {
            return aliyunProperties.getAppCode();
        }

        return null;
    }


}
