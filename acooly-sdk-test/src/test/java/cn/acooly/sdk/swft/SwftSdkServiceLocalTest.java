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
import cn.acooly.sdk.swft.message.QueryOrderStateRequest;
import cn.acooly.sdk.swft.message.QueryOrderStateResponse;
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
 * SWFT单元测试
 *
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
     * 1、查询币种列表
     * <p>
     * 使用SWFT前，需要先查询可以支持的兑换的币种情况，注意：这里查出来的名称才能用于后续兑换的标志参数（比如：通常的`USDT-TRC20`这里采用的是`USDT(TRON)`）。
     * 数据结构为：币编码，币名称，不支持兑换的币列表。例如：QueryCoinListInfo(coinAllCode=USDT(TRON), coinCode=USDT(TRON), coinName=USDT(TRON), isSupportAdvanced=Y, coinImage=null, mainNetwork=TRX, noSupportCoin=VCC,PLA2,WMATIC(MATIC)...)
     * 这里建议定时拉取列表，然后从数据库中查询
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
     * 2.1、获取兑换汇率和手续费信息
     * 该接口主要是获取两种币兑换的费率（以兑换存入币单位为1时的各种手续费），参数：`instantRate`
     * 手续费主要两种：
     * 1). 存币(源币)手续费：该手续费由SWFT收取，一般为源币的0.2%（例如：你充1000USDT币兑换其他币，实际只有9998个币参与兑换，2个币被SWFT收取为佣金），该接口返回参数中的`depositCoinFeeRate`为SWFT平台收取佣金的费率。
     * 2). 链上(目标币)手续费：由矿工收取的链上gas费用等，参数`receiveCoinFee`
     */
    @Test
    public void testGetBaseInfo() {
        String from = "USDT";
        String to = "ETH";
        BaseInfo baseInfo = swftSdkService.getBaseInfo(from, to);
        log.info("兑换汇率 from {} to {} : {}", from, to, baseInfo);
    }

    /**
     * 2.2 根据查询的实时汇率和手续费计算实际能到账的币数量
     * 公式：计算用户兑换实际到账数量： 实际到账数量 = （用户存币数量 - 兑换手续费数量）* 汇率 -  链上发币手续费
     */
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

    /**
     * 单笔订单状态查询
     */
    @Test
    public void testQueryOrderState() {
        String equipmentNo = "211219155207022F0001";
        String sourceType = ChannelEnum.ANDROID.code();
        String orderId = "a3adf843-9209-461c-a732-55c4d9af7c77";

        QueryOrderStateRequest request = new QueryOrderStateRequest(equipmentNo, sourceType, orderId);
        QueryOrderStateResponse response = swftSdkService.queryOrderState(request);
        log.info("QueryOrderState orderId:{}, result: {}", orderId, response.getQueryOrderStateInfo());
    }


}
