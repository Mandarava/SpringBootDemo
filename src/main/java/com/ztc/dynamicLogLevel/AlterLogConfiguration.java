package com.ztc.dynamicLogLevel;

import org.springframework.boot.actuate.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zt on 2017/2/3.
 */
@Configuration
@ConditionalOnWebApplication
public class AlterLogConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AlterLogEndpoint changeLogEndpoint() {
        return new AlterLogEndpoint();
    }

    @Bean
    @ConditionalOnBean(AlterLogEndpoint.class)
    @ConditionalOnEnabledEndpoint(value = "alterLog")
    public AlterLogMvcEndpoint changeLogMvcEndpoint(AlterLogEndpoint delegate) {
        return new AlterLogMvcEndpoint(delegate);
    }
}
