/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-17 11:25
 */
package cn.acooly.sdk.message;

import com.acooly.core.utils.FreeMarkers;
import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import com.google.common.collect.Maps;
import freemarker.template.Template;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-09-17 11:25
 */
@Slf4j
public class SwftMessageBuilder {

    static String inputPath = "/Users/zhangpu/temp/swftmessage.xlsx";

    public static void main(String[] args) throws Exception {
        // 生成报文
        buildMessage();
    }

    public static void buildErrorCodes() throws Exception {
        String path = "/Users/zhangpu/temp/swfterrorCode.xlsx";
        List<SwftMessage> messages = FileParses.loadExcel(path, SwftMessage.class);
        Template template = FreeMarkers.buildConfiguration("classpath:template/").getTemplate("errorcodes.ftl");
        Map<String, Object> map = Maps.newHashMap();
        map.put("messages", messages);
        String result = FreeMarkers.doRenderTemplate(template, map);
        System.out.println(result);
    }


    public static void buildMessage() throws Exception {
        String path = "/Users/zhangpu/temp/swftmessage.xlsx";
        List<SwftMessage> messages = FileParses.loadExcel(path, SwftMessage.class);
        Template template = FreeMarkers.buildConfiguration("classpath:template/").getTemplate("message.ftl");
        Map<String, Object> map = Maps.newHashMap();
        map.put("messages", messages);
        String result = FreeMarkers.doRenderTemplate(template, map);
        System.out.println(result);
    }


    @Data
    public static class SwftMessage {
        @ExcelColumn(index = 0)
        private String title;
        @ExcelColumn(index = 2)
        private String name;
        @ExcelColumn(index = 3)
        private String dateType;
        @ExcelColumn(index = 4)
        private int size;
        @ExcelColumn(index = 5)
        private String isNull;
        @ExcelColumn(index = 6)
        private String memo;

    }

}
