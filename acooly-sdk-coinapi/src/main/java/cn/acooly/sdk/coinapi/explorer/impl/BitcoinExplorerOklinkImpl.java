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

/**
 * BTC浏览器 from btc.com
 *
 * @author zhangpu
 * @date 2021-08-23 10:17
 */
@Slf4j
@Deprecated
public class BitcoinExplorerOklinkImpl extends AbstractCoinExplorer<BitcoinOverview> {


    @Override
    protected BitcoinOverview doBrowser() {
        try {
            // .cookie("next-i18next","zh-CN")
            Document doc = Jsoup.connect("https://www.oklink.com/btc").get();
//            Document doc = Jsoup.parse(loadWithHtmlUnit("https://www.oklink.com/btc"));
            Elements elements = doc.select("div.text-info-box-v");
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (int i = 0; i < elements.size(); i++) {
                Element e = elements.get(i);
                if (e.childNodeSize() != 2) {
                    continue;
                }
                // key
                String key = doParseKey(e.child(0));
                // value
                String value = doParseValue(e.child(1));
                if (Strings.isNotBlank(value)) {
                    sb.append("\"").append(key).append("\":").append(value);
                    if (i < elements.size() - 1) {
                        sb.append(",");
                    }
                }
            }
            sb.append("}");
            String json = sb.toString();
            log.info("Bitcoin Overview: {}", json);
            BitcoinOverview info = JsonMapper.nonDefaultMapper().fromJson(json, BitcoinOverview.class);
            return info;
        } catch (Exception e) {
            log.warn("Bitcoin Overview 错误: {}", e.getMessage());
            throw new BusinessException(CoinApiErrors.DATA_PARSE_ERROR, "Ethereum网络或数据解析错误");
        }
    }
//
//    protected String loadWithHtmlUnit(String url) {
//        WebClient wc = new WebClient(BrowserVersion.CHROME);
//        //是否使用不安全的SSL
//        wc.getOptions().setUseInsecureSSL(true);
//        wc.getOptions().setJavaScriptEnabled(true);
//        wc.getOptions().setThrowExceptionOnScriptError(false);
//        wc.getOptions().setCssEnabled(false);
//        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        wc.getOptions().setActiveXNative(false);
//        //等待js时间 60s
//        wc.waitForBackgroundJavaScript(60 * 1000);
//        //设置Ajax异步处理控制器即启用Ajax支持
//        wc.setAjaxController(new NicelyResynchronizingAjaxController());
//        //设置超时时间
//        wc.getOptions().setTimeout(100 * 1000);
//        //不跟踪抓取
//        wc.getOptions().setDoNotTrackEnabled(false);
//        try {
//            WebRequest request = new WebRequest(new URL(url));
//            request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
////            request.setAdditionalHeader("Cookie", "PLAY_LANG=cn; _plh=b9289d0a863a8fc9c79fb938f15372f7731d13fb; PLATFORM_SESSION=39034d07000717c664134556ad39869771aabc04-_ldi=520275&_lsh=8cf91cdbcbbb255adff5cba6061f561b642f5157&csrfToken=209f20c8473bc0518413c226f898ff79cd69c3ff-1539926671235-b853a6a63c77dd8fcc364a58&_lpt=%2Fcn%2Fvehicle_sales%2Fsearch&_lsi=1646321; _ga=GA1.2.2146952143.1539926675; _gid=GA1.2.1032787565.1539926675; _plh_notime=8cf91cdbcbbb255adff5cba6061f561b642f5157");
//            //模拟浏览器打开一个目标网址
//            HtmlPage htmlPage = wc.getPage(request);
//            //为了获取js执行的数据 线程开始沉睡等待
//            Thread.sleep(10 * 1000);
//            String xml = htmlPage.asXml();
//            System.out.println("HTML：");
//            System.out.println(xml);
//            return xml;
//        } catch (Exception e) {
//            throw new BusinessException("COIN_EXPLORER_HTML_LOAD_FAIL", "浏览器加载HTML失败", e.getMessage());
//        }
//    }


    protected String doParseKey(Element e) {
        return getSubElementText(e, ".okui-popup");
    }

    protected String doParseValue(Element e) {
        return getSubElementText(e, ".hidden-link");
    }

    protected String getSubElementText(Element e, String cssQuery) {
        String val = null;
        Element eVak = e;
        if (eVak != null) {
            Element eVakText = eVak.selectFirst(cssQuery);
            if (eVakText != null) {
                val = eVakText.text();
            }
        }
        return val;
    }


    @Override
    public DigitCurrency coin() {
        return DigitCurrency.btc;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
