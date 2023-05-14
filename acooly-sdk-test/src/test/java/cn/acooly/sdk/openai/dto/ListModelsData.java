/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-26 16:19
 */
package cn.acooly.sdk.openai.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 * @author zhangpu
 * @date 2023-04-26 16:19
 */
@NoArgsConstructor
@Data
@Slf4j
public class ListModelsData {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "object")
    private String object;
    @JSONField(name = "created")
    private Integer created;
    @JSONField(name = "owned_by")
    private String ownedBy;
    @JSONField(name = "permission")
    private List<ListModelsPermission> permission;
    @JSONField(name = "root")
    private String root;
    @JSONField(name = "parent")
    private Object parent;
}
