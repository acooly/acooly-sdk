package cn.acooly.sdk.coinrate;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangpu
 */
@ConfigurationProperties(CoinRateProperties.PREFIX)
@Data
public class CoinRateProperties {
    public static final String PREFIX = "acooly.sdk.coinrate";
    private boolean enable = true;

    /**
     * 天行数据网关配置
     */
    private Gateway tianapi = new Gateway("http://api.tianapi.com/txapi/cybermoney/index",
            "4473ca49b580f6c8987a618362cba8b2", null);


    /**
     * 网关配置
     */
    @Data
    public static class Gateway {
        private String url;
        private String accessKey;
        private String secretKey;
        private long connTimeout = 2 * 1000;
        private long readTimeout = 2 * 1000;

        public Gateway() {
        }

        public Gateway(String url, String accessKey, String secretKey) {
            this.url = url;
            this.accessKey = accessKey;
            this.secretKey = secretKey;
        }
    }

}
