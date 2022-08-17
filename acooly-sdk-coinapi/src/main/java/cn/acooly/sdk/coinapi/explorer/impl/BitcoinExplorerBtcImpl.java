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
import cn.acooly.sdk.coinapi.explorer.domain.BitcoinOverview;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * BTC浏览器 from btc.com
 *
 * @author zhangpu
 * @date 2021-08-23 10:17
 */
@Slf4j
@Component
public class BitcoinExplorerBtcImpl extends AbstractCoinExplorer<BitcoinOverview> {


    @Override
    protected BitcoinOverview doBrowser() {
        try {
            // .cookie("next-i18next","zh-CN")
            Document doc = Jsoup.connect("https://btc.com/btc").timeout(getDefaultTimeoutSeconds() * 1000).get();
            Elements elements = doc.select("div.home_network-status-item__2rgHs");
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (int i = 0; i < elements.size(); i++) {
                Element e = elements.get(i);
                if (e.childNodeSize() != 2) {
                    continue;
                }

                String title = null;
                Element etitle = e.child(0);
                if (etitle != null) {
                    title = etitle.text();
                }
                String key = handleKey(title);
                String value = handleValue(key, e.child(1).text());
                if (Strings.isNotBlank(value)) {
                    sb.append("\"").append(key).append("\":").append(value);
                    if (i < elements.size() - 1) {
                        sb.append(",");
                    }
                }

            }

            String price = doParsePrice(doc);
            if (Strings.isNotBlank(price)) {
                sb.append(", \"price\":").append(price);
            }
            sb.append("}");
            String json = sb.toString();
            log.info("Bitcoin Overview: {}", json);
            BitcoinOverview info = JsonMapper.nonDefaultMapper().fromJson(json, BitcoinOverview.class);
            return info;
        } catch (Exception e) {
            log.warn("Bitcoin Overview 错误: {}", e.getMessage());
            throw new BusinessException(CoinApiErrors.DATA_PARSE_ERROR, "网络或数据解析错误");
        }
    }

    /**
     * 单独解析当前价格（USD）
     *
     * @param doc
     * @return
     * @throws Exception
     */
    protected String doParsePrice(Document doc) throws Exception {
        Elements elements = doc.select("div.home_chain-number__1LLHp");
        String priceText = null;
        for (int i = 0; i < elements.size(); i++) {
            Element e = elements.get(i);
            if (e.childNodeSize() != 2) {
                continue;
            }
            Element ePrice = e.child(0);
            if (ePrice != null) {
                priceText = ePrice.text();
                break;
            }
        }
        priceText = Strings.trimToEmpty(Strings.remove(priceText, "$"));
        priceText = Strings.trimToEmpty(Strings.remove(priceText, ","));
        return priceText;
    }

    @Override
    public DigitCurrency coin() {
        return DigitCurrency.btc;
    }

    @Override
    protected String handleKey(String title) {
        String key = super.handleKey(title);
        if (Strings.equalsIgnoreCase("24HAvgTxrate", key)) {
            key = "tps";
        }
        return key;
    }

    protected String handleValue(String key, String value) {
        if (Strings.equalsIgnoreCase(key, "Difficulty")) {
            return parseDifficulty(value);
        } else if (Strings.startsWithIgnoreCase(key, "earnings")) {
            return parseEarnings(value);
        } else if (Strings.equalsIgnoreCase(key, "expectedNextDifficulty")) {
            return parseNextDifficulty(value);
        } else if (Strings.equalsIgnoreCase(key, "datetoNextDifficulty")) {
            return parseDatetoNextDifficulty(value);
        } else {
            return handleValueToNumber(value);
        }
    }

    protected String parseNextDifficulty(String value) {
        // (+8.91%) 16.94 T
        return handleValueToNumber(Strings.substringAfterLast(value, ")"));
//        return "\"" + Strings.substringBetween(value, "(", ")") + "\"";
    }

    protected String parseDatetoNextDifficulty(String value) {
        // 2 days 14 hrs
        return "\"" + value + "\"";
    }

    protected String parseDifficulty(String value) {
        if (Strings.contains(value, "(")) {
            value = Strings.substringBefore(value, "(");
        }
        return handleValueToNumber(value);
    }

    protected String parseEarnings(String value) {
        String temp = value;
        if (Strings.contains(temp, "=")) {
            temp = Strings.split(temp, "=")[1];
        }
        return handleValueToNumber(temp);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
