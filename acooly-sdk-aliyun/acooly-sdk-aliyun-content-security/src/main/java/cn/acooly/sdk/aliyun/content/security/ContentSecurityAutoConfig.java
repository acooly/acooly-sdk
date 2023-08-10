package cn.acooly.sdk.aliyun.content.security;

import cn.acooly.sdk.aliyun.content.security.service.ContentAuditService;
import cn.acooly.sdk.aliyun.content.security.service.impl.ContentAuditServiceMockImpl;
import cn.acooly.sdk.aliyun.content.security.service.impl.ContentAuditServiceSdkImpl;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpu@acooly.cn
 */
@Configuration
@EnableConfigurationProperties({ContentSecurityProperties.class})
@ConditionalOnProperty(value = ContentSecurityProperties.PREFIX + ".enable", matchIfMissing = true)
@ComponentScan(basePackages = "cn.acooly.sdk.aliyun.content.security")
public class ContentSecurityAutoConfig {

    @Autowired
    private ContentSecurityProperties contentSecurityProperties;

    @Bean
    public IAcsClient iAcsClient() {
        IClientProfile profile = DefaultProfile
                .getProfile(contentSecurityProperties.getRegionId(), contentSecurityProperties.getAppKey(), contentSecurityProperties.getAppSecret());
        return new DefaultAcsClient(profile);
    }

    @Bean
    public ContentAuditService contentAuditService(IAcsClient iAcsClient) {
        if (contentSecurityProperties.getMock()) {
            return new ContentAuditServiceMockImpl();
        }
        return new ContentAuditServiceSdkImpl(iAcsClient);
    }


}
