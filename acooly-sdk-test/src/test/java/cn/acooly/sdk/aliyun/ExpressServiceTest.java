/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-14 13:13
 */
package cn.acooly.sdk.aliyun;

import cn.acooly.sdk.aliyun.express.dto.ExpressTrackInfo;
import cn.acooly.sdk.aliyun.express.enums.CommonExpComp;
import cn.acooly.sdk.aliyun.express.service.ExpressService;
import cn.acooly.sdk.test.NoWebTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangpu
 * @date 2021-10-14 13:13
 */
@Slf4j
public class ExpressServiceTest extends NoWebTestBase {

    @Autowired
    private ExpressService expressService;

    /**
     * 通用查询
     */
    @Test
    public void testTrack() {
        String orderNo = "75812570871893";
        String expCompCode = CommonExpComp.zhongtong.code();
        // first
        ExpressTrackInfo expressTrackInfo = expressService.track(orderNo, expCompCode);
        // second
        expressTrackInfo = expressService.track(orderNo, expCompCode);
        // setting 10s cache timeout
        try {
            // 等待12秒，模拟缓存过期
            Thread.sleep(12 * 1000);
        } catch (Exception e) {
            // ig
        }
        // third
        expressTrackInfo = expressService.track(orderNo, expCompCode);
        log.info("{}", expressTrackInfo);
    }

    /**
     * 顺丰查询
     * 顺丰查询需要提供寄/收件人手机号码的后4位
     */
    @Test
    public void testTrackForShunfeng() {
        String orderNo = "SF1434649690632";
        String expCompCode = CommonExpComp.shunfeng.code();
        String phonePostfix = "7630";
        ExpressTrackInfo expressTrackInfo = expressService.track(orderNo, expCompCode, phonePostfix);
        log.info("{}", expressTrackInfo);
    }

}
