/**
 * acooly-sdk-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-05-31 12:05
 */
package cn.acooly.sdk.exchangerate.service.impl;

import cn.acooly.sdk.exchangerate.dto.ExchangeRates;
import cn.acooly.sdk.exchangerate.enums.LegalCurrency;
import cn.acooly.sdk.exchangerate.service.ExchangeRateService;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Strings;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-05-31 12:05
 */
@Slf4j
@Component
@Primary
public class ExchangeRateMasterImpl
        implements ExchangeRateService, InitializingBean {

    @Autowired
    protected ApplicationContext applicationContext;
    private List<ExchangeRateService> imps = Lists.newArrayList();

    @Override
    public ExchangeRates rates(LegalCurrency currency) {
        for (ExchangeRateService ers : imps) {
            try {
                ExchangeRates er = ers.rates(currency);
                if (er != null) {
                    return er;
                }
            } catch (BusinessException be) {
                log.warn("ERS: {} fail - {}", ers.getName(), be);
                throw be;
            } catch (Exception ex) {
                log.warn("ERS: {} fail - {}", ers.getName(), ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public BigDecimal rate(LegalCurrency from, LegalCurrency to) {
        for (ExchangeRateService ers : imps) {
            try {
                BigDecimal bd = ers.rate(from, to);
                if (bd != null) {
                    return bd;
                }
            } catch (BusinessException be) {
                log.warn("ERS: {} fail - {}", ers.getName(), be);
                throw be;
            } catch (Exception ex) {
                log.warn("ERS: {} fail - {}", ers.getName(), ex.getMessage());
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
            Map<String, ExchangeRateService> targets = applicationContext.getBeansOfType(ExchangeRateService.class);
            for (ExchangeRateService ers : targets.values()) {
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
