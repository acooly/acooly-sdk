/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-23 13:39
 */
package cn.acooly.sdk.coinapi.explorer;

import cn.acooly.sdk.coinapi.enums.DigitCurrency;
import com.acooly.core.common.facade.InfoBase;
import com.acooly.core.utils.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangpu
 * @date 2021-08-23 13:39
 */
@Slf4j
public abstract class AbstractCoinExplorer<T> implements CoinExplorer {

    @Autowired
    protected ExplorerCacheManager explorerCacheManager;


    @Override
    public T browse() {
        T overview = (T) explorerCacheManager.getCache(coin().code());
        if (overview == null) {
            overview = doBrowser();
            explorerCacheManager.setCache(coin().code(), overview);
        }
        return overview;
    }

    protected abstract T doBrowser();

    @Override
    public DigitCurrency coin() {
        return null;
    }

    public void setExplorerCacheManager(ExplorerCacheManager explorerCacheManager) {
        this.explorerCacheManager = explorerCacheManager;
    }


    protected String handleKey(String title) {
        String temp = Strings.removeAll(title, "[\\s,\\.]");
        if (Strings.startsWith(temp, "Earnings")) {
            temp = Strings.remove(temp, "/");
            if (Strings.contains(temp, "(")) {
                String lastPart = Strings.lowerCase(Strings.substringBetween(temp, "(", ")"));
                temp = Strings.split(temp, "(")[0];
                temp = temp + lastPart;
            }
        }
        if (Strings.contains(temp, "/")) {
            temp = Strings.split(temp, "/")[0];
        }
        temp = Strings.uncapitalize(temp);
        return temp;
    }

    protected String handleValueToNumber(String value) {
        String val = Strings.trimToEmpty(value);
        val = Strings.replaceAll(val, "[^0-9.]", "");
        return val;
    }
}
