/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-12-22 18:16
 */
package cn.acooly.sdk.message;

import com.acooly.core.utils.FreeMarkers;
import com.acooly.core.utils.Strings;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-12-22 18:16
 */
@Slf4j
public class Blockchair {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/zhangpu/workspace/acooly/v5/acooly-sdk/acooly-sdk-test/src/test/resources/sources/eth_doc.txt");
        List<String> lines = FileUtils.readLines(file, "UTF-8");
        Map<String, String> data = Maps.newHashMap();
        for (String line : lines) {
            if (Strings.contains(line, "—")) {
                String[] temp = Strings.split(line, "—");
                data.put(Strings.trimToEmpty(named(temp[0])), Strings.trimToEmpty(temp[1]));
            }
        }
        System.out.println(data);


        Template template = FreeMarkers.buildConfiguration("classpath:template/").getTemplate("blockchair_message.ftl");
        Map<String, Object> map = Maps.newHashMap();
        map.put("data", data);
        String result = FreeMarkers.doRenderTemplate(template, map);
        System.out.println(result);

//        String jsonString = FileUtils.readFileToString(file, "UTF-8");
//        JSONObject jsonObject = JSON.parseObject(jsonString);
//        System.out.println(jsonObject.keySet());

    }

    private static String named(String name) {
        if (Strings.contains(name, "_")) {
            String[] temp = Strings.split(name, "_");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < temp.length; i++) {
                if (i == 0) {
                    sb.append(temp[i]);
                } else {
                    sb.append(Strings.capitalize(temp[i]));
                }
            }
            return sb.toString();
        } else {
            return name;
        }
    }
}
