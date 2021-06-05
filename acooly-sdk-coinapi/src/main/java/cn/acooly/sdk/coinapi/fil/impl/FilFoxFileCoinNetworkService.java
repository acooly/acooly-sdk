/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-06 00:18
 */
package cn.acooly.sdk.coinapi.fil.impl;

import cn.acooly.sdk.coinapi.enums.CoinApiErrors;
import cn.acooly.sdk.coinapi.fil.FileCoinNetworkInfo;
import cn.acooly.sdk.coinapi.fil.FileCoinNetworkService;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * filfox 抓取数据实现
 *
 * @author zhangpu
 * @date 2021-06-06 00:18
 */
@Slf4j
@Component
public class FilFoxFileCoinNetworkService implements FileCoinNetworkService {

    @Override
    public FileCoinNetworkInfo overview() {
        try {
            Document doc = Jsoup.connect("https://filfox.info/en").get();
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
                String value = handleValue(e.child(1).text());
                sb.append("\"").append(key).append("\":").append(value);
                if (i < elements.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
            String json = sb.toString();
            log.info("FilFox Overview: {}", json);
            FileCoinNetworkInfo info = JsonMapper.nonDefaultMapper().fromJson(json, FileCoinNetworkInfo.class);
            return info;
        } catch (Exception e) {
            log.warn("FilFox 全网数据解析 错误: {}",e.getMessage());
            throw new BusinessException(CoinApiErrors.DATA_PARSE_ERROR,"FilFox网络或数据解析错误");
        }
    }

    private String handleKey(String title) {
        String key = Strings.trimToEmpty(title);
        if (Strings.startsWith(key, "24h")) {
            key = Strings.replace(key, "24h", "period");
        }
        key = Strings.replaceAll(key, StringUtils.SPACE, "");
        key = Strings.uncapitalize(key);
        return key;
    }

    private String handleValue(String value) {
        String val = Strings.trimToEmpty(value);
        val = Strings.replaceAll(val, "[^0-9.]", "");
        return val;
    }
}
