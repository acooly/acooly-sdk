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
    /**
     * 网络代理
     */
    private Proxy proxy = new Proxy();

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
    }

    /**
     * 代理配置
     */
    @Data
    public static class Proxy {

        /**
         * 是否开启代理，默认false
         */
        private boolean enable = false;

        /**
         * 代理服务地址
         */
        private String host = "127.0.0.1";
        /**
         * 代理服务端口
         */
        private int port = 19180;
    }


}
