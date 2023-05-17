/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-05-14 21:30
 */
package cn.acooly.sdk.openai;

import cn.acooly.sdk.openai.domain.chat.ChatMessage;
import cn.acooly.sdk.openai.domain.chat.ChatRequest;
import cn.acooly.sdk.openai.domain.chat.ChatResponse;
import cn.acooly.sdk.openai.service.OpenAiSdkService;
import cn.acooly.sdk.openai.support.JsonHttpTransport;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2023-05-14 21:30
 */
@Slf4j
public class OpenAiSdkServiceTest extends OpenAiProxyTest {

    protected OpenAiSdkService openAiSdkService;

    @Test
    public void testChat() {
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.addMessage(new ChatMessage("system", "瑞幸咖啡"));
        chatRequest.addMessage(new ChatMessage("user", "奶油是植物奶油还是动物奶油？"));
        chatRequest.addMessage(new ChatMessage("assistant", "根据瑞幸咖啡官网上的信息，他们使用的奶油是动物奶油。"));
        chatRequest.addMessage(new ChatMessage("user", "每天一杯生椰丝绒拿铁加奶油影响大吗？"));
        ChatResponse response = openAiSdkService.chat(chatRequest);
        log.info("{}", response);
    }

    @Test
    public void testListModels() {
        log.info("{}", openAiSdkService.listModels());
    }

    @Before
    public void setUp() throws Exception {
        OpenAiSdkProperties properties = new OpenAiSdkProperties();
        OpenAiSdkProperties.Gateway gateway = new OpenAiSdkProperties.Gateway();
        gateway.setKey(super.apiKey);
        OpenAiSdkProperties.Proxy proxy = new OpenAiSdkProperties.Proxy();
        proxy.setEnable(true);
        proxy.setHost("127.0.0.1");
        proxy.setPort(19180);
        properties.setGateway(gateway);
        properties.setProxy(proxy);
        JsonHttpTransport jsonHttpTransport = new JsonHttpTransport(properties);
        openAiSdkService = new OpenAiSdkService(jsonHttpTransport);
        log.info("本地手工初始化openAiSdkService完成。");
    }

}
