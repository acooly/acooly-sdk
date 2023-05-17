/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-05-14 20:36
 */
package cn.acooly.sdk.openai.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author zhangpu
 * @date 2023-05-14 20:36
 */
@NoArgsConstructor
@Data
@Slf4j
public class Usage {
    @JSONField(name = "prompt_tokens")
    private Integer promptTokens;
    @JSONField(name = "completion_tokens")
    private Integer completionTokens;
    @JSONField(name = "total_tokens")
    private Integer totalTokens;
}
