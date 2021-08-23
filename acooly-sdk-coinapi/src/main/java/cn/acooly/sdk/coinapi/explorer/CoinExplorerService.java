/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-08-23 21:42
 */
package cn.acooly.sdk.coinapi.explorer;

import cn.acooly.sdk.coinapi.enums.DigitCurrency;
import cn.acooly.sdk.coinapi.explorer.domain.BitcoinOverview;
import cn.acooly.sdk.coinapi.explorer.domain.EthereumOverview;
import cn.acooly.sdk.coinapi.explorer.domain.FilecoinOverview;
import com.acooly.core.utils.Collections3;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-08-23 21:42
 */
@Slf4j
@Component
public class CoinExplorerService implements ApplicationContextAware, InitializingBean {

    protected ApplicationContext applicationContext;
    protected Map<DigitCurrency, List<CoinExplorer>> container = Maps.newHashMap();

    public <T> T overview(DigitCurrency digitCurrency) {
        List<CoinExplorer> coinExplorers = container.get(digitCurrency);
        if (Collections3.isEmpty(coinExplorers)) {
            return null;
        }
        T overview = null;
        for (CoinExplorer coinExplorer : coinExplorers) {
            overview = (T) coinExplorer.browse();
            if (overview != null) {
                break;
            }
        }
        return overview;
    }

    public BitcoinOverview btc() {
        return overview(DigitCurrency.btc);
    }


    public EthereumOverview eth() {
        return overview(DigitCurrency.eth);
    }

    public FilecoinOverview fil() {
        return overview(DigitCurrency.fil);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, CoinExplorer> coinExplorers = this.applicationContext.getBeansOfType(CoinExplorer.class);
        for (CoinExplorer coinExplorer : coinExplorers.values()) {
            List<CoinExplorer> subCoinExplorers = container.get(coinExplorer.coin());
            if (subCoinExplorers == null) {
                subCoinExplorers = Lists.newArrayList();
                container.put(coinExplorer.coin(), subCoinExplorers);
            }
            subCoinExplorers.add(coinExplorer);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
