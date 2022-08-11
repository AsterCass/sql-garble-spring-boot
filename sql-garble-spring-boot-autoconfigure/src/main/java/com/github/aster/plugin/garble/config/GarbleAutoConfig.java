package com.github.aster.plugin.garble.config;

import com.github.aster.plugin.garble.interceptor.GarbleUpdateInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;

@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
@EnableConfigurationProperties(GarbleConfig.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class GarbleAutoConfig {

    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Resource
    private GarbleConfig garbleConfig;


    @PostConstruct
    public void addGarbleInterceptor() {
        GarbleUpdateInterceptor interceptor = new GarbleUpdateInterceptor();
        Properties properties = new Properties();
        properties.putAll(garbleConfig.getPropertiesMap());
        interceptor.setProperties(properties);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }


}
