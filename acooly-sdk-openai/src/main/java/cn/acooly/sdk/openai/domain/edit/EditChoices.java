/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-05-17 16:20
 */
package cn.acooly.sdk.openai.domain.edit;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author zhangpu
 * @date 2023-05-17 16:20
 */
@NoArgsConstructor
@Data
@Slf4j
public class EditChoices {
    @JSONField(name = "text")
    private String text;
    @JSONField(name = "index")
    private Integer index;
}
