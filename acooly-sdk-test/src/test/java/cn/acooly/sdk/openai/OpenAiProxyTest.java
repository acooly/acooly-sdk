/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-27 14:20
 */
package cn.acooly.sdk.openai;

import com.acooly.core.utils.Strings;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.util.Properties;

/**
 * 代理方式测试基类
 *
 * @author zhangpu
 * @date 2023-04-27 14:20
 */
@Slf4j
public class OpenAiProxyTest {

    protected String apiGateway = "https://api.openai.com";
    // 为保证秘钥安全，请在本地开发环境配置文件`application-local.properties`中设置测试用的OPENAI_API_KEY参数`acooly.sdk.openai.gateway.token`
    protected String apiKey = "";


    @Before
    public void before() {
        loadProperties();
    }

    protected String doPost(String url, String body) {
        String endPointUrl = Strings.isHttpUrl(url) ? url : apiGateway + url;
        return HttpRequest.get(endPointUrl)
                .useProxy("127.0.0.1", 19180)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .send(body).body();
    }

    protected String doGet(String url) {
        String endPointUrl = Strings.isHttpUrl(url) ? url : apiGateway + url;
        return HttpRequest.get(endPointUrl)
                .useProxy("127.0.0.1", 19180)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .body();
    }

    @Test
    public void testLoadProperties() {

    }

    public void loadProperties() {
        String localProperties = "application-local.properties";
        Properties props = new Properties();
        InputStream is = null;
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource("classpath:" + localProperties);
            is = resource.getInputStream();
            props.load(is);
        } catch (Exception e) {
            // ig
        } finally {
            IOUtils.closeQuietly(is);
        }

        apiGateway = Strings.isBlankDefault(props.getProperty("acooly.sdk.openai.gateway.url"), apiGateway);
        apiKey = Strings.isBlankDefault(props.getProperty("acooly.sdk.openai.gateway.token"), apiKey);
        if (Strings.isBlank(apiKey)) {
            throw new RuntimeException("请在配置文件''中，设置测试用的OPENAI_API_KEY参数`acooly.sdk.openai.gateway.token`");
        }
        log.info("merge properties from {}: apiGateway={}, apiKey={}", localProperties, apiGateway, apiKey);
    }

}
