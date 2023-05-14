/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-05-14 20:36
 */
package cn.acooly.sdk.openai.domain.chat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangpu
 * @date 2023-05-14 20:36
 */
@NoArgsConstructor
@Data
@Slf4j
public class ChatChoices {
    @JSONField(name = "message")
    private ChatChoicesMessage message;
    @JSONField(name = "finish_reason")
    private String finishReason;
    @JSONField(name = "index")
    private Integer index;
}
