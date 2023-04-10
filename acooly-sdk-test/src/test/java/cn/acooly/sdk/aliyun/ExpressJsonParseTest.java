/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-09 22:23
 */
package cn.acooly.sdk.aliyun;

import cn.acooly.sdk.aliyun.express.dto.tianyan.ExpResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2023-04-09 22:23
 */
@Slf4j
public class ExpressJsonParseTest {

    @Test
    public void testJsonParse() {

        String json = "{\n" +
                "    \"msg\": \"成功\",\n" +
                "    \"success\": true,\n" +
                "    \"code\": 200,\n" +
                "    \"data\": {\n" +
                "        \"orderNo\": \"296141744182247312\",\n" +
                "        \"info\": [\n" +
                "            {\n" +
                "                \"cpCode\": \"ZTO\",\n" +
                "                \"theLastTime\": \"2022-05-09 22:45:05\",\n" +
                "                \"mailNo\": \"75876648253690\",\n" +
                "                \"theLastMessage\": \"您的快递已签收, 签收人在【多多买菜的xxx幢一单元】(xxx一单元)领取。如有疑问请电联:（13711112222）, 投诉电话:（13711112222）, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】\",\n" +
                "                \"cpMobile\": \"95311\", \n" +
                "                \"logisticsCompanyName\": \"中通快递\", \n" +
                "                \"cpUrl\": \"https://www.zto.com/\",\n" +
                "                \"takeTime\": \"2天21小时45分\", \n" +
                "                \"logisticsStatusDesc\": \"已签收\",\n" +
                "                \"logisticsTraceDetailList\": [\n" +
                "                    {\n" +
                "                        \"areaCode\": \"CN530100000000\", \n" +
                "                        \"areaName\": \"云南省,昆明市\",\n" +
                "                        \"subLogisticsStatus\": \"ACCEPT\", \n" +
                "                        \"time\": 1651840444000, \n" +
                "                        \"logisticsStatus\": \"ACCEPT\", \n" +
                "                        \"desc\": \"【昆明经开民办科技园】（952270、15711111111） 的 匡光明（15522222222） 已揽收\" \n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"areaCode\": \"CN530100000000\",\n" +
                "                        \"areaName\": \"云南省,昆明市\",\n" +
                "                        \"subLogisticsStatus\": \"TRANSPORT\",\n" +
                "                        \"time\": 1651840449000,\n" +
                "                        \"logisticsStatus\": \"TRANSPORT\",\n" +
                "                        \"desc\": \"快件离开 【昆明经开民办科技园】 已发往 【昆明中转】\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"subLogisticsStatus\": \"STA_INBOUND\",\n" +
                "                        \"time\": 1652091544000,\n" +
                "                        \"logisticsStatus\": \"DELIVERING\",\n" +
                "                        \"desc\": \"快件已被【多多买菜的鹤岭19幢一单元】代收，如有问题请电联（13711112222），感谢使用中通快递，期待再次为您服务！\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"subLogisticsStatus\": \"STA_SIGN\",\n" +
                "                        \"time\": 1652107505000,\n" +
                "                        \"logisticsStatus\": \"SIGN\",\n" +
                "                        \"desc\": \"您的快递已签收, 签收人在【多多买菜的xxx幢一单元】(鹤岭xxx一单元)领取。如有疑问请电联:（13711112222）, 投诉电话:（13711112222）, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"logisticsStatus\": \"SIGN\" \n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";


        ExpResult expResult = JSON.parseObject(json, ExpResult.class);
        log.info("\n{}", expResult);
    }

}
