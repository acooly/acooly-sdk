/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-03 14:39
 */
package cn.acooly.sdk.coinapi.service.impl;

import cn.acooly.sdk.coinapi.dto.TianapiResult;
import cn.acooly.sdk.coinapi.dto.Ticker;
import cn.acooly.sdk.coinapi.enums.CoinApiProvider;
import cn.acooly.sdk.coinapi.service.CoinApiService;
import cn.acooly.sdk.coinapi.service.CoinApiServiceAbstractImpl;
import com.acooly.core.utils.Asserts;
import com.acooly.core.utils.Collections3;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * 币行情天行数据实现
 * <p>收费(2021-06-03)：
 * <li>免费会员: 100次/天</li>
 * <li>高级会员1万次/天 20元/月，120元/年</li>
 * <li>黄金会员50万次/天 65/月，390/年</li>
 * </p>
 *
 * @author zhangpu
 * @date 2021-06-03 14:39
 */
@Slf4j
@Component
public class TianapiCoinApiService extends CoinApiServiceAbstractImpl implements CoinApiService {

    @Override
    protected Ticker doTicker(String symbol) {
        Asserts.notEmpty(coinApiProperties.getTianapi().getUrl(), "网关地址");
        Asserts.notEmpty(coinApiProperties.getTianapi().getAccessKey(), "ApiKey");
        String requestUrl = coinApiProperties.getTianapi().getUrl()
                + "/txapi/cybermoney/index?key=" + coinApiProperties.getTianapi().getAccessKey()
                + "&coin=" + symbol;
        String resp = HttpRequest.get(requestUrl).contentType(HttpRequest.CONTENT_TYPE_JSON)
                .connectTimeout(coinApiProperties.getTianapi().getConnTimeout() * 1000)
                .readTimeout(coinApiProperties.getTianapi().getReadTimeout() * 1000)
                .body();
        TianapiResult result = jsonMapper.fromJson(resp, TianapiResult.class);
        log.info("CoinApi.ticker [{}] : {}", this.getName(), result);
        return Collections3.getFirst(result.getNewslist());

    }

    @PostConstruct
    public void init() {
        // 注册日期转换格式
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonMapper.getMapper().setDateFormat(smt);
        jsonMapper.getMapper().setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    @Override
    public String getName() {
        return CoinApiProvider.tianapi.code();
    }

    @Override
    public int getOrder() {
        return CoinApiProvider.tianapi.ordinal();
    }


}
