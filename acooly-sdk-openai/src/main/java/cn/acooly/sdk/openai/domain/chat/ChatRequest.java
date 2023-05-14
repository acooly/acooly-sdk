/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-27 14:22
 */
package cn.acooly.sdk.openai.domain.chat;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author zhangpu
 * @date 2023-04-27 14:22
 */
@NoArgsConstructor
@Data
@Slf4j
public class ChatRequest {

    /**
     * 模式ID
     * 具体可选请跟进listModels接口查询获取
     * 可选：gpt-4, gpt-4-0314, gpt-4-32k, gpt-4-32k-0314, gpt-3.5-turbo, gpt-3.5-turbo-0301
     *
     * @see https://platform.openai.com/docs/models/model-endpoint-compatibility
     */
    @NotBlank
    private String model = "gpt-3.5-turbo";

    /**
     * 会话消息列表
     * 一般由三种角色的消息组成：系统（插件/主题）、用户（用户询问的问题）、机器人（AI响应，前面回答的问题）
     * 一般由一个system消息开始，然后是多个用户消息+机器消息的会话
     */
    @NotEmpty
    private List<ChatMessage> messages = Lists.newArrayList();

    /**
     * 温度
     * 通过该参数控制AI回答问题的确定性，取值范围0~2，默认为1，值越大，回答的内容越不确定，越有可能出现奇怪的回答。
     * 与top_p参数互斥，不能同时使用。
     */
    private Integer temperature = 1;

    /**
     * 返回的可能答案的TOP个数
     * 默认1，表示100%全部，0.1表示10%，0.01表示1%。
     */
    @JSONField(name = "top_p")
    private Integer topP;

    /**
     * Defaults to 1
     * How many chat completion choices to generate for each input message.
     */
    @JSONField(name = "n")
    private Integer n;

    @JSONField(name = "stream")
    private Boolean stream;

    @JSONField(name = "stop")
    private String stop;

    @JSONField(name = "max_tokens")
    private Integer maxTokens;

    @JSONField(name = "presence_penalty")
    private Integer presencePenalty;

    @JSONField(name = "frequency_penalty")
    private Integer frequencyPenalty;

    @JSONField(name = "logit_bias")
    private Object logitBias;

    @JSONField(name = "user")
    private String user;

    public ChatRequest(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }
}
