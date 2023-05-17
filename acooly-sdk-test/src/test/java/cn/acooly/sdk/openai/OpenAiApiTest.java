/**
 * acooly-openai
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-22 21:11
 */
package cn.acooly.sdk.openai;

import cn.acooly.sdk.openai.dto.ListModels;
import cn.acooly.sdk.openai.dto.ListModelsData;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2023-04-22 21:11
 */
@Slf4j
public class OpenAiApiTest extends OpenAiProxyTest {

    /**
     * 支持的Models列表
     */
    @Test
    public void editText() {
        String url = "https://api.openai.com/v1/edits";
        Map<String, Object> map = new HashMap<>();
        map.put("model", "text-davinci-edit-001");
        map.put("input", "今天星几？");
        map.put("instruction", "Fix the spelling mistakes");
        String result = doPost(url, JSON.toJSONString(map));
        log.info("edits result:\n{}", result);
    }


    @Test
    public void editCode() {
        String url = "https://api.openai.com/v1/edits";
        Map<String, Object> map = new HashMap<>();
        map.put("model", "code-davinci-edit-001");
        map.put("input", "if(1=1){\n    System.out.println(\"Hello World!\");\n}");
        map.put("instruction", "Fix the code mistakes");
        String result = doPost(url, JSON.toJSONString(map));
        log.info("edits result:\n{}", result);
    }

    /**
     * 支持的Models列表
     */
    @Test
    public void listModels() {
        String url = "https://api.openai.com/v1/models";
        String result = doGet(url);
        log.info("listModels result:\n{}", result);
    }

    @Test
    public void listModelsResult() {
        String url = "https://api.openai.com/v1/models";
        String result = doGet(url);
        log.info("listModels result:\n{}", result);
        ListModels listModels = JSON.parseObject(result, ListModels.class);

        log.info("listModels size: {}", listModels.getData().size());
        for (ListModelsData data : listModels.getData()) {
            log.info("{}", data.getId());
        }
    }

    /**
     * 查询单个Model
     */
    @Test
    public void retrieveModel() {
        String modelId = "gpt-3.5-turbo-0301";
        String url = "https://api.openai.com/v1/models/" + modelId;
        String result = doGet(url);
        log.info("Retrieve Model {} : \n{}", modelId, result);
    }


    @Test
    public void completions() {
        String url = "https://api.openai.com/v1/completions";
        Map<String, Object> map = new HashMap<>();
        map.put("model", "text-davinci-003");
        map.put("prompt", "瑞幸咖啡的奶油健康吗？");
        map.put("temperature", 0.5);
        map.put("max_tokens", 500);

        String jsonBody = JSON.toJSONString(map);
        String result = doPost(url, jsonBody);
        System.out.println(result);
    }


}
