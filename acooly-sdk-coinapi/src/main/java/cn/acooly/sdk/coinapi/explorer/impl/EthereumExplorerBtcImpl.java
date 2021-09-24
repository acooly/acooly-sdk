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
import cn.acooly.sdk.coinapi.explorer.domain.EthereumOverview;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * ETH浏览器 from btc.com
 *
 * @author zhangpu
 * @date 2021-08-23 10:17
 */
@Slf4j
@Component
public class EthereumExplorerBtcImpl extends AbstractCoinExplorer<EthereumOverview> {


    @Override
    protected EthereumOverview doBrowser() {
        try {
            // .cookie("next-i18next","zh-CN")
            Document doc = Jsoup.connect("https://btc.com/eth").timeout(getDefaultTimeoutSeconds() * 1000).get();
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
            sb.append("}");
            String json = sb.toString();
            log.info("Ethereum Overview: {}", json);
            EthereumOverview info = JsonMapper.nonDefaultMapper().fromJson(json, EthereumOverview.class);
            return info;
        } catch (Exception e) {
            log.warn("Ethereum Overview 错误: {}", e.getMessage());
            throw new BusinessException(CoinApiErrors.DATA_PARSE_ERROR, "Ethereum网络或数据解析错误");
        }
    }


    @Override
    public DigitCurrency coin() {
        return DigitCurrency.eth;
    }


    @Override
    protected String handleKey(String title) {
        if (Strings.equalsIgnoreCase(title, "TPS")) {
            return "tps";
        }
        return super.handleKey(title);
    }

    protected String handleValue(String key, String value) {
        if (Strings.equalsIgnoreCase(key, "Difficulty")) {
            return parseDifficulty(value);
        } else if (Strings.startsWithIgnoreCase(key, "earnings")) {
            return parseEarnings(value);
        } else {
            return handleValueToNumber(value);
        }
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

}
