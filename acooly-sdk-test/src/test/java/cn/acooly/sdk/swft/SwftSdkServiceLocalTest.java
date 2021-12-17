/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-18 14:15
 */
package cn.acooly.sdk.swft;

import cn.acooly.sdk.swft.message.AccountExchangeRequest;
import cn.acooly.sdk.swft.message.dto.BaseInfo;
import cn.acooly.sdk.swft.message.dto.ExchangeCaleResult;
import cn.acooly.sdk.swft.message.dto.QueryCoinListInfo;
import cn.acooly.sdk.swft.transport.HttpSwftTransport;
import cn.acooly.sdk.swft.transport.SwftTransport;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Ids;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
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

    /**
     * 获取兑换汇率和手续费信息
     */
    @Test
    public void testGetBaseInfo() {
        String from = "ETH";
        String to = "USDT";
        BaseInfo baseInfo = swftSdkService.getBaseInfo(from, to);
        log.info("兑换汇率 from {} to {} : {}", from, to, baseInfo);
    }

    @Test
    public void testCalcExchange() {
        String from = "ETH";
        String to = "USDT";
        BigDecimal fromAmount = BigDecimal.valueOf(2);
        ExchangeCaleResult result = swftSdkService.exchangeCalc(from, fromAmount, to);
        log.info("汇率计算 from {}{} to {}{}, full data: {}", fromAmount, from, result.getReceiveCoinAmount(), to, result);
    }

    /**
     * 创建闪兑订单
     */
    @Test
    public void testAccountExchange() {
        String refundAddr = "";

        AccountExchangeRequest request = new AccountExchangeRequest();
        request.setDepositCoinCode("USDT");
        request.setDepositCoinAmt("1");
        request.setEquipmentNo(Ids.did());
        request.setSourceFlag("wecoinbank");
        request.setRefundAddr(refundAddr);
//        request.setDestinationAddr();

    }


}
