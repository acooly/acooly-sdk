package cn.acooly.sdk.coinapi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangpu
 */
@ConfigurationProperties(CoinApiProperties.PREFIX)
@Data
public class CoinApiProperties {
    public static final String PREFIX = "acooly.sdk.coinapi";
    private boolean enable = true;

    /**
     * 数字货币浏览器配置
     */
    private Explorer explorer = new Explorer();

    /**
     * 缓存配置
     */
    private Cache cache = new Cache();

    /**
     * 蜜蜂查
     */
    private Gateway mifengcha = new Gateway("https://data.mifengcha.com/api",
            null, null);

    /**
     * 天行数据
     */
    private Gateway tianapi = new Gateway("http://api.tianapi.com",
            null, null);


    @Data
    public static class Cache {
        /**
         * 缓存开关，默认开启
         */
        private boolean enable = true;
        /**
         * 缓存超时时长(秒)，默认2分钟
         */
        private long timeout = 2 * 60;

        /**
         * 缓存大小
         */
        private int size = 20;
    }


    /**
     * 网关配置
     */
    @Data
    public static class Gateway {
        /**
         * 网关地址
         */
        private String url;
        /**
         * 身份Key/ApiKey
         */
        private String accessKey;
        /**
         * 安全码（需要签名的情况）
         */
        private String secretKey;
        /**
         * 连接超时时长(秒)
         */
        private int connTimeout = 10;
        /**
         * 读取超时时长(秒)
         */
        private int readTimeout = 5;


        public Gateway() {
        }

        public Gateway(String url, String accessKey, String secretKey) {
            this.url = url;
            this.accessKey = accessKey;
            this.secretKey = secretKey;
        }
    }

    @Data
    public static class Explorer {
        private Cache cache = new Cache();
    }

}
