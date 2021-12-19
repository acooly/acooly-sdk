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
import cn.acooly.sdk.swft.message.AccountExchangeResponse;
import cn.acooly.sdk.swft.message.dto.BaseInfo;
import cn.acooly.sdk.swft.message.dto.ExchangeCaleResult;
import cn.acooly.sdk.swft.message.dto.QueryCoinListInfo;
import cn.acooly.sdk.swft.transport.HttpSwftTransport;
import cn.acooly.sdk.swft.transport.SwftTransport;
import com.acooly.core.common.enums.ChannelEnum;
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
        System.setProperty("https.proxySet", "true");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "19180");
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
        String from = "USDT";
        String to = "ETH";
        BaseInfo baseInfo = swftSdkService.getBaseInfo(from, to);
        log.info("兑换汇率 from {} to {} : {}", from, to, baseInfo);
    }

    @Test
    public void testCalcExchange() {
        String from = "USDT(TRON)";
        String to = "ETH";
        BigDecimal fromAmount = BigDecimal.valueOf(100);
        ExchangeCaleResult result = swftSdkService.exchangeCalc(from, fromAmount, to);
        log.info("汇率计算 from {}{} to {}{}, full data: {}", fromAmount, from, result.getReceiveCoinAmount(), to, result);
    }

    /**
     * 创建闪兑订单
     * Requ {"depositCoinAmt":"150","depositCoinCode":"USDT(TRON)","destinationAddr":"0x5ab5666f5b9107a0cb1d7fc0c64a4e4c927bd427","equipmentNo":"211219155207022F0001","receiveCoinAmt":"0.026079997495600","receiveCoinCode":"ETH","refundAddr":"TUdJeFqPBMwV8q1iNWs8r6Dgr4XpSHXbon","service":"/api/v2/accountExchange","sourceFlag":"wecoinbank","sourceType":"ANDROID"}
     * Resp {"data":{"chainFee":"0.011522","changeType":"advanced","choiseFeeType":"3","createTime":"2021-12-19 15:52:12","dealFinishTime":null,"depositCoinAmt":"150","depositCoinCode":"USDT(TRON)","depositCoinFeeAmt":"0.3","depositCoinFeeRate":"0.002","depositCoinState":"wait_send","depositFeeRate":"0.002","depositTxid":"","destinationAddr":"0x5ab5666f5b9107a0cb1d7fc0c64a4e4c927bd427","detailState":"wait_deposit_send","developerId":"","instantRate":"0.000251240388","isDiscount":"N","kycUrl":"","orderId":"a3adf843-9209-461c-a732-55c4d9af7c77","orderState":"wait_deposits","platformAddr":"TBzGqdGXRMUpVeKYuzgsSGJPeVEiHAqNhn","receiveCoinAmt":"0.037681","receiveCoinCode":"ETH","receiveSwftAmt":"111.01","refundAddr":"TUdJeFqPBMwV8q1iNWs8r6Dgr4XpSHXbon","refundCoinAmt":"","refundCoinMinerFee":"","refundDepositTxid":"","refundSwftAmt":"","swftCoinFeeRate":"0.001","swftCoinState":"","swftReceiveAddr":"","swftRefundAddr":"","transactionId":"","xrpInfo":""},"resCode":"800","resMsg":"成功","resMsgEn":""}
     */
    @Test
    public void testAccountExchange() {

        String depositCoinCode = "USDT(TRON)";
        BigDecimal depositCoinAmount = BigDecimal.valueOf(150);
        // 退款地址（源币地址）
        String depositCoinAddr = "TUdJeFqPBMwV8q1iNWs8r6Dgr4XpSHXbon";
        String receiveCoinCode = "ETH";
        String receiveCoinAddr = "0x5ab5666f5b9107a0cb1d7fc0c64a4e4c927bd427";
        ExchangeCaleResult result = swftSdkService.exchangeCalc(depositCoinCode, depositCoinAmount, receiveCoinCode);
        BigDecimal receiveCoinAmount = result.getReceiveCoinAmount();

        AccountExchangeRequest request = new AccountExchangeRequest();
        // 公共参数
        request.setEquipmentNo(Ids.did());
        request.setSourceType(ChannelEnum.ANDROID.code());
        request.setSourceFlag("wecoinbank");

        request.setDepositCoinCode(depositCoinCode);
        request.setDepositCoinAmt(depositCoinAmount.toPlainString());
        request.setRefundAddr(depositCoinAddr);

        request.setReceiveCoinCode(receiveCoinCode);
        request.setReceiveCoinAmt(receiveCoinAmount.toPlainString());
        request.setDestinationAddr(receiveCoinAddr);

        AccountExchangeResponse response = swftSdkService.accountExchange(request);
        log.info("AccountExchangeResponse: {}", response);

    }


}
