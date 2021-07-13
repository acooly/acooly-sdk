package cn.acooly.sdk.filecoin;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangpu
 */
@ConfigurationProperties(FilecoinSdkProperties.PREFIX)
@Data
@NoArgsConstructor
public class FilecoinSdkProperties {
    public static final String PREFIX = "acooly.sdk.filecoin";
    private boolean enable = true;

    /**
     * 网关配置
     */
    private Gateway gateway = new Gateway();

    /**
     * 网关配置
     */
    @Data
    @ToString
    public static class Gateway {
        /**
         * 网关地址
         */
        private String url;
        /**
         * auth token
         */
        private String token;

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
            this.token = token;
        }
    }

    public FilecoinSdkProperties(String url, String token) {
        this.gateway.setUrl(url);
        this.gateway.setToken(token);
    }
}
