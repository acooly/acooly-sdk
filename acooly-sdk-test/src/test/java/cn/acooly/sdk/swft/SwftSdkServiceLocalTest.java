/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-18 14:15
 */
package cn.acooly.sdk.swft;

import cn.acooly.sdk.swft.message.QueryCoinListRequest;
import cn.acooly.sdk.swft.message.QueryCoinListResponse;
import cn.acooly.sdk.swft.message.dto.QueryCoinListInfo;
import cn.acooly.sdk.swft.transport.HttpSwftTransport;
import cn.acooly.sdk.swft.transport.SwftTransport;
import com.acooly.core.utils.Collections3;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author zhangpu
 * @date 2021-09-18 14:15
 */
@Slf4j
public class SwftSdkServiceLocalTest {

    private SwftSdkService swftSdkService;

    @Before
    public void init() {
        SwftProperties swftProperties = new SwftProperties();
        SwftTransport swftTransport = new HttpSwftTransport(swftProperties);
        swftSdkService = new SwftSdkService(swftTransport, swftProperties);
    }

    /**
     * 查询币种列表
     */
    @Test
    public void testQueryCoinList() {
        List<QueryCoinListInfo> queryCoinListInfos = swftSdkService.queryCoinList();
        if (Collections3.isNotEmpty(queryCoinListInfos)) {
            for (QueryCoinListInfo info : queryCoinListInfos) {
                log.info("{}", info);
            }
        }
    }


}
