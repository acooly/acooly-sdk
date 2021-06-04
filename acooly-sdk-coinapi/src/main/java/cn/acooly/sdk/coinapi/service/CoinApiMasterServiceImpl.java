/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-31 12:05
 */
package cn.acooly.sdk.coinapi.service;

import cn.acooly.sdk.coinapi.dto.Ticker;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.core.OrderComparator;
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
public class CoinApiMasterServiceImpl
        implements CoinApiService, InitializingBean {

    @Autowired
    protected ApplicationContext applicationContext;
    private List<CoinApiService> imps = Lists.newArrayList();

    @Override
    public Ticker ticker(String symbol) {
        for (CoinApiService ers : imps) {
            try {
                Ticker ticker = ers.ticker(symbol);
                if (ticker != null) {
                    return ticker;
                }
            } catch (BusinessException be) {
                log.warn("CoinApi.ticker [{}] fail: {}", ers.getName(), be.getMessage());
            } catch (Exception e) {
                log.warn("CoinApi.ticker [{}] fail: {}", ers.getName(), e.getMessage());
            }
        }
        return null;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getOrder() {
        return -1;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            Map<String, CoinApiService> targets = applicationContext.getBeansOfType(CoinApiService.class);
            for (CoinApiService ers : targets.values()) {
                if (Strings.isBlank(ers.getName())) {
                    continue;
                }
                imps.add(ers);
            }
            OrderComparator.sort(imps);
            imps.forEach(
                    p -> log.info("加载 CoinApi Provider: {} -> {}", p.getName(), p.getClass().getName()));
        } catch (Exception e) {
            throw new RuntimeException("Spring容器自定义代理接口初始化失败", e);
        }
    }
}
