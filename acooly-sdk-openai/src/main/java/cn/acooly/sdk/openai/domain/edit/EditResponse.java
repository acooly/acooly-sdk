/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-05-17 16:19
 */
package cn.acooly.sdk.openai.domain.edit;

import cn.acooly.sdk.openai.domain.Usage;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author zhangpu
 * @date 2023-05-17 16:19
 */
@NoArgsConstructor
@Data
@Slf4j
public class EditResponse {

    @JSONField(name = "object")
    private String object;
    @JSONField(name = "created")
    private Integer created;
    @JSONField(name = "choices")
    private List<EditChoices> choices;
    @JSONField(name = "usage")
    private Usage usage;
}
