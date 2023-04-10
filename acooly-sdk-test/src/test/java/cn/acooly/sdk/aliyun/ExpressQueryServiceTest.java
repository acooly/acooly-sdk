/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-10-14 13:13
 */
package cn.acooly.sdk.aliyun;

import cn.acooly.sdk.aliyun.express.domain.ExpressInfo;
import cn.acooly.sdk.aliyun.express.enums.CommonExpComp;
import cn.acooly.sdk.aliyun.express.service.ExpressQueryService;
import cn.acooly.sdk.test.NoWebTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangpu
 * @date 2023-04-10
 */
@Slf4j
public class ExpressQueryServiceTest extends NoWebTestBase {

    @Autowired
    private ExpressQueryService expressQueryService;

    /**
     * 通用查询
     */
    @Test
    public void query() {
        String orderNo = "75609357909263";
        String expCompCode = CommonExpComp.zhongtong.code();
        // first
        ExpressInfo expressInfo = expressQueryService.query(orderNo, null, null);
        // second
        expressInfo = expressQueryService.query(orderNo);
        log.info("{}", expressInfo);
    }

    /**
     * 顺丰查询
     * 顺丰查询需要提供寄/收件人手机号码的后4位
     */
    @Test
    public void testTrackForShunfeng() {
        String orderNo = "SF1616507942921";
        String expCompCode = CommonExpComp.shunfeng.code();
        String phonePostfix = "13896177630";
        ExpressInfo expressTrackInfo = expressQueryService.query(orderNo, phonePostfix);
        log.info("{}", expressTrackInfo);
    }

}
