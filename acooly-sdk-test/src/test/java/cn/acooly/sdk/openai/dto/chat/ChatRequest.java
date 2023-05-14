/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-27 14:22
 */
package cn.acooly.sdk.openai.dto.chat;

import com.alibaba.fastjson.annotation.JSONField;
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


    @NotBlank
    private String model;

    @NotEmpty
    private List<ChatMessage> messages;

    private Integer temperature = 1;

    @JSONField(name = "top_p")
    private Integer topP;

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

}
