/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-09-16 23:04
 */
package cn.acooly.sdk.swft;

import cn.acooly.sdk.swft.message.*;
import cn.acooly.sdk.swft.message.dto.AccountExchangeInfo;
import cn.acooly.sdk.swft.message.dto.BaseInfo;
import cn.acooly.sdk.swft.message.dto.QueryCoinListInfo;
import cn.acooly.sdk.swft.transport.SwftTransport;
import com.acooly.core.utils.Strings;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
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
        return swftTransport.send(request, AccountExchangeResponse.class);
    }

    /**
     * 订单状态查询
     *
     * @param request
     * @return
     */
    public QueryOrderStateResponse queryOrderState(QueryOrderStateRequest request) {
        return swftTransport.send(request, QueryOrderStateResponse.class);
    }

    /**
     * 查询所有交易
     *
     * @param request
     * @return
     */
    public QueryAllTradeResponse queryAllTrade(QueryAllTradeRequest request) {
        return swftTransport.send(request, QueryAllTradeResponse.class);
    }

    public SwftSdkService(SwftTransport swftTransport, SwftProperties swftProperties) {
        this.swftTransport = swftTransport;
        this.swftProperties = swftProperties;
    }
}
