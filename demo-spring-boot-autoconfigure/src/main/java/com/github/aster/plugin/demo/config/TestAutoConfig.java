package com.github.aster.plugin.demo.config;

import com.github.aster.plugin.demo.service.TestService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TestConfig.class)
public class TestAutoConfig {

    @ConditionalOnMissingBean(TestService.class)
    @Bean
    public TestService testService() {
        return new TestService();
    }


}
