/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-27 14:22
 */
package cn.acooly.sdk.openai.domain.edit;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * 修正/修订请求报文
 *
 * @author zhangpu
 * @date 2023-04-27 14:22
 */
@NoArgsConstructor
@Data
@Slf4j
public class EditRequest {

    /**
     * 模式ID
     * ID of the model to use. You can use the text-davinci-edit-001 or code-davinci-edit-001 model with this endpoint.
     */
    @NotBlank
    private String model = "text-davinci-edit-001";

    /**
     * The instruction that tells the model how to edit the prompt.
     */
    @NotBlank
    private String instruction;

    /**
     * 原始输入
     * The input text to use as a starting point for the edit.
     */
    private String input;

    /**
     * 温度
     * 通过该参数控制AI回答问题的确定性，取值范围0~2，默认为1，值越大，回答的内容越不确定，越有可能出现奇怪的回答。
     * 与top_p参数互斥，不能同时使用。
     */
    private Integer temperature = 1;

    /**
     * 返回的可能答案的TOP个数
     * 默认1，表示100%全部，0.1表示10%，0.01表示1%。
     */
    @JSONField(name = "top_p")
    private Integer topP;

    /**
     * Defaults to 1
     * How many chat completion choices to generate for each input message.
     */
    @JSONField(name = "n")
    private Integer n;
}
