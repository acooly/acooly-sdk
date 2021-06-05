/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-06-05 23:40
 */
package cn.acooly.sdk;

import cn.acooly.sdk.coinapi.fil.FileCoinNetworkInfo;
import cn.acooly.sdk.coinapi.fil.impl.FilFoxFileCoinNetworkService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2021-06-05 23:40
 */
@Slf4j
public class FilfoxTest {

    @Test
    public void jsoapTest() throws Exception {

        Document doc = Jsoup.connect("https://filfox.info/zh").get();
        System.out.println(doc.title());

        Elements elements = doc.select("div.flex.items-center.rounded-sm.bg-background > div");
        for (Element e : elements) {
//            log.info("classes: {}", e.classNames());
//            log.info("children size: {}", e.childNodeSize());
            if (e.childNodeSize() != 2) {
                continue;
            }

            Element titleElement = e.child(0);
            Element valueElement = e.child(1);

            String title = titleElement.child(0).text();
            String value = valueElement.text();

            System.out.println(title + " : " + value);

        }

    }

    @Test
    public void testFilFoxFileCoinNetworkService(){
        FilFoxFileCoinNetworkService fns = new FilFoxFileCoinNetworkService();
        FileCoinNetworkInfo fileCoinNetworkInfo = fns.overview();
        System.out.println(fileCoinNetworkInfo);
    }


}
