package cn.acooly.sdk.aliyun.express;

import cn.acooly.sdk.aliyun.common.transport.HttpTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpu@acooly.cn
 */
@Configuration
@EnableConfigurationProperties({AliyunExpressProperties.class})
@ConditionalOnProperty(value = AliyunExpressProperties.PREFIX + ".enable", matchIfMissing = true)
public class AliyunExpressAutoConfig {

    @Autowired
    private AliyunExpressProperties aliyunExpressProperties;

    @Bean
    public HttpTransport aliyunExpressTransport() {
        HttpTransport transport = new HttpTransport(aliyunExpressProperties);
        return transport;
    }

}
