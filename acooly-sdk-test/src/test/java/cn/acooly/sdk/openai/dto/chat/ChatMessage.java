/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-27 14:24
 */
package cn.acooly.sdk.openai.dto.chat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * @author zhangpu
 * @date 2023-04-27 14:24
 */
@NoArgsConstructor
@Data
@Slf4j
public class ChatMessage {

    @NotBlank
    @JSONField(name = "role")
    private String role;

    @NotBlank
    @JSONField(name = "content")
    private String content;

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
