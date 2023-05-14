package cn.acooly.sdk.openai;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangpu
 */
@ConfigurationProperties(OpenAiSdkProperties.PREFIX)
@Data
@NoArgsConstructor
public class OpenAiSdkProperties {
    public static final String PREFIX = "acooly.sdk.openai";
    private boolean enable = true;

    /**
     * 网关配置
     */
    private Gateway gateway = new Gateway();

    public OpenAiSdkProperties(String url, String token) {
        this.gateway.setUrl(url);
        this.gateway.setKey(token);
    }

    /**
     * 网关配置
     */
    @Data
    public static class Gateway {
        /**
         * 网关地址
         */
        private String url = "https://api.openai.com";
        /**
         * API_KEY
         */
        private String key;

        /**
         * 连接超时时长(秒)
         */
        private int connTimeout = 30;
        /**
         * 读取超时时长(秒)
         */
        private int readTimeout = 10;


        public Gateway() {
        }

        public Gateway(String url, String token) {
            this.url = url;
            this.key = token;
        }
    }
}
