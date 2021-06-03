/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-31 12:05
 */
package cn.acooly.sdk.coinrate.service.impl;

import cn.acooly.sdk.coinrate.dto.Ticker;
import cn.acooly.sdk.coinrate.service.CoinRateService;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-05-31 12:05
 */
@Slf4j
@Component
@Primary
public class MasterCoinRateServiceImpl
        implements CoinRateService, InitializingBean {

    @Autowired
    protected ApplicationContext applicationContext;
    private List<CoinRateService> imps = Lists.newArrayList();

    @Override
    public Ticker ticker(String symbol) {
        for (CoinRateService ers : imps) {
            try {
                Ticker ticker = ers.ticker(symbol);
                if (ticker != null) {
                    return ticker;
                }
            } catch (BusinessException be) {
                log.warn("IconRate: {} fail - {}", ers.getName(), be);
                throw be;
            } catch (Exception ex) {
                log.warn("IconRate: {} fail - {}", ers.getName(), ex.getMessage());
            }
        }
        return null;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            Map<String, CoinRateService> targets = applicationContext.getBeansOfType(CoinRateService.class);
            for (CoinRateService ers : targets.values()) {
                if (Strings.isBlank(ers.getName())) {
                    continue;
                }
                imps.add(ers);
            }
        } catch (Exception e) {
            throw new RuntimeException("Spring容器自定义代理接口初始化失败", e);
        }
    }
}
