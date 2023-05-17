/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-05-14 18:49
 */
package cn.acooly.sdk.openai.service;

import cn.acooly.sdk.openai.domain.chat.ChatRequest;
import cn.acooly.sdk.openai.domain.chat.ChatResponse;
import cn.acooly.sdk.openai.domain.edit.EditRequest;
import cn.acooly.sdk.openai.domain.edit.EditResponse;
import cn.acooly.sdk.openai.domain.model.Model;
import cn.acooly.sdk.openai.domain.model.Models;
import cn.acooly.sdk.openai.support.JsonHttpTransport;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

/**
 * @author zhangpu
 * @date 2023-05-14 18:49
 */
@Slf4j
@Component
public class OpenAiSdkService {

    @Autowired
    private JsonHttpTransport jsonHttpTransport;

    public OpenAiSdkService(JsonHttpTransport jsonHttpTransport) {
        this.jsonHttpTransport = jsonHttpTransport;
    }

    /**
     * 列表支持的模式
     *
     * @return
     * @see <a href="https://platform.openai.com/docs/models">Models</a>
     * @see <a href="https://platform.openai.com/docs/api-reference/models">list-models API</a>
     */
    public Models listModels() {
        String url = "/v1/models";
        String body = jsonHttpTransport.get(url);
        Models models = JSON.parseObject(body, Models.class);
        return models;
    }

    /**
     * 获取单个模型详情
     *
     * @param modelId
     * @return
     */
    public Model retrieveModel(@NotBlank String modelId) {
        String url = "/v1/models/" + modelId;
        String body = jsonHttpTransport.get(url);
        Model model = JSON.parseObject(body, Model.class);
        return model;
    }

    /**
     * Chat会话
     *
     * @param chatRequest
     * @return
     */
    public ChatResponse chat(ChatRequest chatRequest) {
        String url = "/v1/chat/completions";
        String body = jsonHttpTransport.post(url, JSON.toJSONString(chatRequest));
        ChatResponse response = JSON.parseObject(body, ChatResponse.class);
        return response;
    }

    /**
     * AI修正
     * 支持的模式：
     * text-davinci-edit-001：修正文本
     * code-davinci-edit-001：修正代码
     *
     * @param editRequest
     * @return
     */
    public EditResponse edits(EditRequest editRequest) {
        String url = "/v1/edits";
        String body = jsonHttpTransport.post(url, JSON.toJSONString(editRequest));
        EditResponse response = JSON.parseObject(body, EditResponse.class);
        return response;
    }

}
