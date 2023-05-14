/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-27 14:19
 */
package cn.acooly.sdk.openai;

import cn.acooly.sdk.openai.dto.chat.ChatMessage;
import cn.acooly.sdk.openai.dto.chat.ChatRequest;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @author zhangpu
 * @date 2023-04-27 14:19
 */
@Slf4j
public class OpenAiChatTest extends OpenAiProxyTest {


    @Test
    public void chat() {
        String url = "https://api.openai.com/v1/chat/completions";

        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setModel("gpt-3.5-turbo");
        List<ChatMessage> messages = Lists.newArrayList();
        messages.add(new ChatMessage("system", "瑞幸咖啡"));
        messages.add(new ChatMessage("user", "奶油是植物奶油还是动物奶油？"));
        messages.add(new ChatMessage("assistant", "根据瑞幸咖啡官网上的信息，他们使用的奶油是动物奶油。"));
        messages.add(new ChatMessage("user", "是那种动物奶油呢？是否有反式脂肪酸？"));
        messages.add(new ChatMessage("assistant", "瑞幸咖啡官网上没有明确指出他们使用的是哪种动物奶油，但一般来说，咖啡店所使用的奶油多数是牛奶制成的。至于是否含有反式脂肪酸，则需要进一步查询相关营养成分信息。不过，反式脂肪酸在奶油中的含量较低，相比于一些烘焙食品或加工食品，奶油对反式脂肪酸的贡献较小。"));
        messages.add(new ChatMessage("user", "我可以安心的加奶油了吧？"));
        messages.add(new ChatMessage("assistant", "瑞幸咖啡使用的奶油应该是安全的，但建议适量食用。奶油的主要成分是脂肪，过量摄入可能会增加能量摄入，导致肥胖等健康问题。此外，奶油虽然营养丰富，但仍然是高脂肪、高热量的食品，不适合长期大量食用。"));
        messages.add(new ChatMessage("user", "每天一杯生椰丝绒拿铁加奶油影响大吗？"));
        chatRequest.setMessages(messages);

        String jsonBody = JSON.toJSONString(chatRequest);
        String result = doPost(url, jsonBody);

        System.out.println(result);
    }

}
