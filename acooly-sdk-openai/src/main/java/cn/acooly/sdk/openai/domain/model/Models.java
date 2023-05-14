/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-26 16:18
 */
package cn.acooly.sdk.openai.domain.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 * @author zhangpu
 * @date 2023-04-26 16:18
 */
@NoArgsConstructor
@Data
@Slf4j
public class Models {

    @JSONField(name = "object")
    private String object;
    @JSONField(name = "data")
    private List<Model> data;
}
