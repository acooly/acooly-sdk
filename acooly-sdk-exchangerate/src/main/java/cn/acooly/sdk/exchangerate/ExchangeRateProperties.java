package cn.acooly.sdk.exchangerate;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangpu
 */
@ConfigurationProperties(ExchangeRateProperties.PREFIX)
@Data
public class ExchangeRateProperties {
    public static final String PREFIX = "acooly.sdk.exchangerate";
    private boolean enable = true;

}
