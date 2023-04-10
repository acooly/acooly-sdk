/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-04-10 15:32
 */
package cn.acooly.sdk.aliyun.express;

import cn.acooly.sdk.aliyun.express.domain.ExpressInfo;
import cn.acooly.sdk.aliyun.express.service.ExpressQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpu
 * @date 2023-04-10 15:32
 */
@Slf4j
@RestController
@RequestMapping("/test/express")
public class ExpressQueryTestController {

    @Autowired
    private ExpressQueryService expressQueryService;

    @RequestMapping("query")
    public ExpressInfo query(@RequestParam("mailNo") String mailNo, @RequestParam("mobileNo") String mobileNo) {
        ExpressInfo expressInfo = expressQueryService.query(mailNo, mobileNo);
        log.info("{}", expressInfo);
        return expressInfo;
    }

}
