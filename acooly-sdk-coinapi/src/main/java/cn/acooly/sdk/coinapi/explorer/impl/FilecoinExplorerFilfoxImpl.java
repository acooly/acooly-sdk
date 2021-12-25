/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-23 10:17
 */
package cn.acooly.sdk.coinapi.explorer.impl;

import cn.acooly.sdk.coinapi.enums.CoinApiErrors;
import cn.acooly.sdk.coinapi.enums.DigitCurrency;
import cn.acooly.sdk.coinapi.explorer.AbstractCoinExplorer;
import cn.acooly.sdk.coinapi.explorer.domain.FilecoinOverview;
import cn.acooly.sdk.coinapi.quote.BinanceQuoteService;
import cn.acooly.sdk.coinapi.quote.dto.CoinQuote;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.concurrent.CountDownLatch;

/**
 * FIL浏览器 from filfox.info
 *
 * @author zhangpu
 * @date 2021-08-23 10:17
 */
@Slf4j
@Component
public class FilecoinExplorerFilfoxImpl extends AbstractCoinExplorer<FilecoinOverview> {

    @Autowired
    private BinanceQuoteService binanceQuoteService;

    @Override
    protected FilecoinOverview doBrowser() {
        FilecoinOverview filecoinOverview = loadAndParse();
        if(filecoinOverview != null){
            filecoinOverview.setPrice(getPrice());
        }
        return filecoinOverview;
    }

    protected FilecoinOverview loadAndParse(){
        try {
            Document doc = Jsoup.connect("https://filfox.info/en").timeout(getDefaultTimeoutSeconds() * 1000).get();
            Elements elements = doc.select("div.flex.items-center.rounded-sm.bg-background > div");
            StringBuilder sb = new StringBuilder();
            sb.append("{");

            for (int i = 0; i < elements.size(); i++) {
                Element e = elements.get(i);
                if (e.childNodeSize() != 2) {
                    continue;
                }
                String title = null;
                Element etitle = e.child(0);
                if (etitle != null && etitle.child(0) != null) {
                    title = etitle.child(0).text();
                }
                String key = handleKey(title);
                String value = handleValueToNumber(e.child(1).text());
                sb.append("\"").append(key).append("\":").append(value);
                if (i < elements.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
            String json = sb.toString();
            log.info("FilFox Overview: {}", json);
            FilecoinOverview info = JsonMapper.nonDefaultMapper().fromJson(json, FilecoinOverview.class);
            return info;
        } catch (Exception e) {
            log.warn("FilFox 全网数据解析 错误: {}", e.getMessage());
            throw new BusinessException(CoinApiErrors.DATA_PARSE_ERROR, "FilFox网络或数据解析错误");
        }
    }

    protected Money getPrice() {
        try {
            CoinQuote quote = binanceQuoteService.quoteUsdt("FIL");
            return new Money(quote.getPrice().getAmount(), Currency.getInstance("USD"));
        } catch (Exception e) {
            // ig
        }
        return null;
    }


    @Override
    protected String handleKey(String title) {
        String key = Strings.trimToEmpty(title);
        if (Strings.startsWith(key, "24h")) {
            key = Strings.replace(key, "24h", "period");
        }
        key = Strings.replaceAll(key, StringUtils.SPACE, "");
        key = Strings.uncapitalize(key);
        return key;
    }

    @Override
    public DigitCurrency coin() {
        return DigitCurrency.fil;
    }


    public void setBinanceQuoteService(BinanceQuoteService binanceQuoteService) {
        this.binanceQuoteService = binanceQuoteService;
    }
}
