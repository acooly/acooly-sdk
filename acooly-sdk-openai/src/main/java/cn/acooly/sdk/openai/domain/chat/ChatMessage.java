/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-27 14:24
 */
package cn.acooly.sdk.openai.domain.chat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 一条chat会话消息
 * A list of messages describing the conversation so far.
 *
 * @author zhangpu
 * @date 2023-04-27 14:24
 */
@NoArgsConstructor
@Data
@Slf4j
public class ChatMessage {

    /**
     * 消息角色
     * The role of the author of this message. One of system, user, or assistant.
     * system: 系统消息，一般用于对话开始，告诉AI本次会话的主题,或者是提示，比如：翻译以下的文字；做总结等。
     * user: 用户消息，也就是用户文档问题
     * assistant: AI助手消息，也就是AI的回答
     */
    @NotBlank
    @JSONField(name = "role")
    private String role;

    /**
     * 消息内容
     */
    @NotBlank
    @JSONField(name = "content")
    private String content;

    /**
     * 消息名称
     * 可以用于标记改消息的作者，由大小写字母+数字组成，长度不超过64位
     */
    @Size(max = 64)
    @JSONField(name = "name")
    private String name;

    public ChatMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public ChatMessage(String name, String role, String content) {
        this.name = name;
        this.role = role;
        this.content = content;
    }
}
