/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-26 16:19
 */
package cn.acooly.sdk.openai.domain.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author zhangpu
 * @date 2023-04-26 16:19
 */
@NoArgsConstructor
@Data
@Slf4j
public class ModelPermission {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "object")
    private String object;
    @JSONField(name = "created")
    private Integer created;
    @JSONField(name = "allow_create_engine")
    private Boolean allowCreateEngine;
    @JSONField(name = "allow_sampling")
    private Boolean allowSampling;
    @JSONField(name = "allow_logprobs")
    private Boolean allowLogprobs;
    @JSONField(name = "allow_search_indices")
    private Boolean allowSearchIndices;
    @JSONField(name = "allow_view")
    private Boolean allowView;
    @JSONField(name = "allow_fine_tuning")
    private Boolean allowFineTuning;
    @JSONField(name = "organization")
    private String organization;
    @JSONField(name = "group")
    private Object group;
    @JSONField(name = "is_blocking")
    private Boolean isBlocking;
}
