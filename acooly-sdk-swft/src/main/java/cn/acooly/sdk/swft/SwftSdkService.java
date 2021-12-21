/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-16 23:04
 */
package cn.acooly.sdk.swft;

import cn.acooly.sdk.swft.enums.SwftDetailState;
import cn.acooly.sdk.swft.message.*;
import cn.acooly.sdk.swft.message.dto.*;
import cn.acooly.sdk.swft.transport.SwftTransport;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.BeanCopier;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

/**
 * 闪兑SDK
 * 文档：https://docs.google.com/spreadsheets/d/1LgjKhNGQRL1_TJmzrZfLjqW-FGWcJDI6uuzjuB7aBRM/edit#gid=821282425
 * <p>
 * <h3>核心流程及逻辑</h3>
 *
 * <ol>
 * <li>`queryCoinList` : 查询支持的币种（以及每个币种的编码，不支持兑换的币种列表）列表，检测采用定时拉取，本地缓存方式</li>
 * <li>`getBaseInfo` : 查看币种兑换的汇率（两种币的兑换汇率），限制参数（最大最小兑换额度）和手续费（SWFT平台费率和链上费用）</li>
 * <li>`exchangeCalc` : 根据`getBaseInfo`的数据，计算指定两种币，指定额度兑换后的数量</li>
 * <li>
 *     `accountExchange` : 闪兑订单接口，提交兑换订单请求。
 *     <ul>
 *      <li>请求后，SWFT会返回一个对源币的平台存币地址</li>
 *      <li>兑换这想找个地址存入订单对应的源币</li>
 *      <li>SWFT先根据SWFT手续费费率收取SWFT手续费（收源币）</li>
 *      <li>SWFT会把剩下的源币按汇率兑换收币币种数量，然后充币到用户的收币地址（链上扣取费用）</li>
 *     </ul>
 * </li>
 * <li>`queryOrderState`：单笔订单状态查询接口</li>
 * <li>`queryAllTrade`：批量订单分页查询接口</li>
 * </ol>
 * </p>
 *
 * @author zhangpu
 * @date 2021-09-16 23:04
 */
@Slf4j
@NoArgsConstructor
@Component
public class SwftSdkService {

    @Autowired
    private SwftTransport swftTransport;
    @Autowired
    private SwftProperties swftProperties;

    /**
     * 查询币种列表
     * /api/v1/queryCoinList
     *
     * @param request
     * @return
     */
    public QueryCoinListResponse queryCoinList(QueryCoinListRequest request) {
        return swftTransport.send(request, QueryCoinListResponse.class);
    }

    /**
     * 查询币种列表
     * /api/v1/queryCoinList
     *
     * @return
     * @see cn.acooly.sdk.swft.SwftSdkService#queryCoinList(QueryCoinListRequest)
     */
    public List<QueryCoinListInfo> queryCoinList() {
        QueryCoinListResponse response = queryCoinList(new QueryCoinListRequest());
        return response.getQueryCoinListInfos();
    }

    /**
     * 查询指定币种间的汇率
     * /api/v1/getBaseInfo
     *
     * @param request
     * @return 汇率详情
     * @see cn.acooly.sdk.swft.SwftSdkService#getBaseInfo(GetBaseInfoRequest)
     */
    public GetBaseInfoResponse getBaseInfo(GetBaseInfoRequest request) {
        return swftTransport.send(request, GetBaseInfoResponse.class);
    }

    /**
     * 查询指定币种间的汇率
     *
     * @param depositCoinCode 存入货币编码(from)
     * @param receiveCoinCode 接收货币编码(to)
     * @return
     */
    public BaseInfo getBaseInfo(@NotEmpty String depositCoinCode, @NotEmpty String receiveCoinCode) {
        GetBaseInfoRequest request = new GetBaseInfoRequest(depositCoinCode, receiveCoinCode);
        GetBaseInfoResponse response = getBaseInfo(request);
        return response.getBaseInfo();
    }


    /**
     * 汇率计算
     * <p>
     * 可以用于界面上及时计算
     * 计算用户兑换实际到账数量： 实际到账数量 = （用户存币数量 - 兑换手续费数量）* 汇率 -  链上发币手续费
     * receiveCoinAmt = （depositCoinAmt - depositCoinAmt * 兑换手续费率） *  instantRate - receiveCoinFee
     *
     * @param depositCoinCode
     * @param depositCoinAmount
     * @param receiveCoinCode
     * @return
     */
    public ExchangeCaleResult exchangeCalc(String depositCoinCode, BigDecimal depositCoinAmount, String receiveCoinCode) {

        ExchangeCaleResult result = new ExchangeCaleResult();
        BaseInfo baseInfo = getBaseInfo(depositCoinCode, receiveCoinCode);
        result.setChainFee(baseInfo.getChainFee());
        result.setDepositMax(baseInfo.getDepositMax());
        result.setDepositCoinFeeRate(baseInfo.getDepositCoinFeeRate());
        result.setDepositMin(baseInfo.getDepositMin());
        result.setInstantRate(baseInfo.getInstantRate());
        result.setIsDisCount(baseInfo.getIsDisCount());
        result.setMinerFee(baseInfo.getMinerFee());
        result.setReceiveCoinFee(baseInfo.getReceiveCoinFee());

//        BeanCopier.copy(baseInfo, result);
        // swft兑换手续费率
        BigDecimal depositCoinFeeRate = baseInfo.getDepositCoinFeeRate();
        // swft兑换手续费
        BigDecimal swftFee = depositCoinAmount.multiply(depositCoinFeeRate);
        // 链上发币手续费
        BigDecimal receiveCoinFee = baseInfo.getChainFee();
        // 汇率
        BigDecimal instantRate = baseInfo.getInstantRate();
        log.info("兑换 from depositCoin: {} {} to receiveCoinCode:{}", depositCoinAmount, depositCoinCode, receiveCoinCode);
//        log.info("depositCoinFeeRate: {}, depositCoinFee:{}", depositCoinFeeRate, swftFee);
//        log.info("instantRate: {}, receiveCoinFee:{}", instantRate, receiveCoinFee);
        // 计算兑换后应收币数量
        BigDecimal receiveCoinAmount = depositCoinAmount.subtract(swftFee).multiply(instantRate).subtract(receiveCoinFee);
        log.info("收币数量[receiveCoinAmount]公式计算：{} = (depositCoinAmt[{}] - depositCoinAmt[{}] * depositCoinFeeRate[{}]） *  instantRate[{}] - receiveCoinFee[{}]",
                receiveCoinAmount, depositCoinAmount, depositCoinAmount, depositCoinFeeRate, instantRate, receiveCoinFee);
        result.setReceiveCoinAmount(receiveCoinAmount);
        result.setDepositCoinAmount(depositCoinAmount);
        result.setDepositCoinFree(swftFee);
        return result;
    }


    /**
     * 闪兑接口
     *
     * @param request
     * @return
     */
    public AccountExchangeResponse accountExchange(AccountExchangeRequest request) {
        // 设置订单创建来源
        if (Strings.isBlank(request.getSourceFlag())) {
            request.setSourceFlag(swftProperties.getSourceFlag());
        }
        AccountExchangeResponse response = swftTransport.send(request, AccountExchangeResponse.class);
        // 处理detailState对应的中文说明
        if (response.getAccountExchangeInfo() != null && Strings.isNotBlank(response.getAccountExchangeInfo().getDetailState())) {
            response.getAccountExchangeInfo().setDetailStateText(getDetailStateText(response.getAccountExchangeInfo().getDetailState()));
        }
        return response;
    }

    /**
     * 订单状态查询
     * （单笔查询）
     *
     * @param request
     * @return
     */
    public QueryOrderStateResponse queryOrderState(QueryOrderStateRequest request) {
        QueryOrderStateResponse response = swftTransport.send(request, QueryOrderStateResponse.class);
        // 处理detailState对应的中文说明
        if (response.getQueryOrderStateInfo() != null && Strings.isNotBlank(response.getQueryOrderStateInfo().getDetailState())) {
            response.getQueryOrderStateInfo().setDetailStateText(getDetailStateText(response.getQueryOrderStateInfo().getDetailState()));
        }
        return response;
    }

    /**
     * 查询所有交易
     * (分页查询)
     *
     * @param request
     * @return
     */
    public TradeOrderPageInfo queryAllTrade(QueryAllTradeRequest request) {
        QueryAllTradeResponse response = swftTransport.send(request, QueryAllTradeResponse.class);
        TradeOrderPageInfo pageInfo = response.getTradeOrderPageInfo();
        if (Collections3.isNotEmpty(pageInfo.getTradeOrderInfos())) {
            for (TradeOrderInfo orderInfo : pageInfo.getTradeOrderInfos()) {
                if (Strings.isNotBlank(orderInfo.getDetailState())) {
                    orderInfo.setDetailStateText(getDetailStateText(orderInfo.getDetailState()));
                }
            }
        }
        return pageInfo;
    }

    public SwftSdkService(SwftTransport swftTransport, SwftProperties swftProperties) {
        this.swftTransport = swftTransport;
        this.swftProperties = swftProperties;
    }

    private String getDetailStateText(String detailState) {
        if (Strings.isNotBlank(detailState)) {
            SwftDetailState swftDetailState = SwftDetailState.find(detailState);
            if (swftDetailState != null) {
                return swftDetailState.getMessage();
            }
        }
        return null;
    }
}
