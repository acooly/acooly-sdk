/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-05-14 20:35
 */
package cn.acooly.sdk.openai.domain.chat;

import com.acooly.core.utils.Collections3;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author zhangpu
 * @date 2023-05-14 20:35
 */
@NoArgsConstructor
@Data
@Slf4j
public class ChatResponse {

    @JSONField(name = "id")
    private String id;
    @JSONField(name = "object")
    private String object;
    @JSONField(name = "created")
    private Integer created;
    @JSONField(name = "model")
    private String model;
    @JSONField(name = "usage")
    private ChatUsage usage;
    @JSONField(name = "choices")
    private List<ChatChoices> choices;

    /**
     * 返回最佳匹配的响应内容
     *
     * @return
     */
    public String getFirstContent() {
        if (Collections3.isEmpty(choices)) {
            return null;
        }
        ChatChoices first = Collections3.getFirst(choices);
        if (first.getMessage() == null) {
            return null;
        }
        return first.getMessage().getContent();
    }

}
