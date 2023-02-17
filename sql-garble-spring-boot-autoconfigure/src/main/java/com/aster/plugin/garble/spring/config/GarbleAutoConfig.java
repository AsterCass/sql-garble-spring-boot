package com.aster.plugin.garble.spring.config;

import com.aster.plugin.garble.enums.AuthenticationTypeEnum;
import com.aster.plugin.garble.enums.GarbleFunctionEnum;
import com.aster.plugin.garble.interceptor.GarbleQueryInterceptor;
import com.aster.plugin.garble.interceptor.GarbleUpdateInterceptor;
import com.aster.plugin.garble.service.SpecifiedMethodGenerator;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author astercasc
 */
@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
@EnableConfigurationProperties({GarbleConfig.class, AuthSelectConfig.class,
        UpdatedDataMsgConfig.class, AuthInsertConfig.class})
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class GarbleAutoConfig {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Resource
    private GarbleConfig garbleConfig;

    @Resource
    private AuthSelectConfig authSelectConfig;

    @Resource
    private AuthInsertConfig authInsertConfig;

    @Resource
    private UpdatedDataMsgConfig updatedDataMsgConfig;

    private GarbleUpdateInterceptor garbleUpdateInterceptor;

    private GarbleQueryInterceptor garbleQueryInterceptor;


    @PostConstruct
    public void addGarbleInterceptor() {
        if (garbleConfig.getValid() && null != garbleConfig.getGarbleFunctionList()) {

            Set<Interceptor> interceptorList = new HashSet<>();


            if (garbleConfig.getGarbleFunctionList().contains(GarbleFunctionEnum.UPDATED_DATA_MSG.getCode())) {
                GarbleUpdateInterceptor interceptor = getSingleGarbleUpdateInterceptor();
                Properties properties = new Properties();
                properties.putAll(updatedDataMsgConfig.getPropertiesMap());
                //spring 获取对象
                Map<Method, Object> methodObjectMap = new HashMap<>();
                List<Method> methods =
                        SpecifiedMethodGenerator.loadUpdatedMsgBySubTypes(
                                properties.getProperty("dealWithUpdatedPath"));
                if (0 != methods.size()) {
                    for (Method method : methods) {
                        methodObjectMap.put(method,
                                applicationContext.getBean(method.getDeclaringClass()));
                    }
                }
                interceptor.setUpdatedDataMsgProperty(properties, methodObjectMap);
                interceptorList.add(interceptor);
            }
            if (garbleConfig.getGarbleFunctionList().contains(GarbleFunctionEnum.SELECT_AUTHENTICATION.getCode())) {
                GarbleQueryInterceptor interceptor = getSingleGarbleQueryInterceptor();
                Properties properties = new Properties();
                properties.putAll(authSelectConfig.getPropertiesMap());
                //spring 获取对象
                Map<Method, Object> methodObjectMap = new HashMap<>();
                List<Method> methods =
                        SpecifiedMethodGenerator.loadAuthCodeBySubTypes(
                                properties.getProperty("authCodePath"),
                                AuthenticationTypeEnum.SELECT);
                if (0 != methods.size()) {
                    for (Method method : methods) {
                        methodObjectMap.put(method,
                                applicationContext.getBean(method.getDeclaringClass()));
                    }
                }
                interceptor.setAuthenticationFilterSelectProperty(properties, methodObjectMap);
                interceptorList.add(interceptor);
            }
            if (garbleConfig.getGarbleFunctionList().contains(GarbleFunctionEnum.INSERT_AUTHENTICATION.getCode())) {
                GarbleUpdateInterceptor interceptor = getSingleGarbleUpdateInterceptor();
                Properties properties = new Properties();
                properties.putAll(authInsertConfig.getPropertiesMap());
                //spring 获取对象
                Map<Method, Object> methodObjectMap = new HashMap<>();
                List<Method> methods =
                        SpecifiedMethodGenerator.loadAuthCodeBySubTypes(
                                properties.getProperty("authCodePath"),
                                AuthenticationTypeEnum.INSERT);
                if (0 != methods.size()) {
                    for (Method method : methods) {
                        methodObjectMap.put(method,
                                applicationContext.getBean(method.getDeclaringClass()));
                    }
                }
                interceptor.setInsertAuthProperty(properties, methodObjectMap);
                interceptorList.add(interceptor);
            }

            for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
                for (Interceptor interceptor : interceptorList) {
                    sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
                }
            }

        }

    }

    private GarbleUpdateInterceptor getSingleGarbleUpdateInterceptor() {
        if (null == this.garbleUpdateInterceptor) {
            this.garbleUpdateInterceptor = new GarbleUpdateInterceptor();
        }
        return this.garbleUpdateInterceptor;

    }

    private GarbleQueryInterceptor getSingleGarbleQueryInterceptor() {
        if (null == this.garbleQueryInterceptor) {
            this.garbleQueryInterceptor = new GarbleQueryInterceptor();
        }
        return this.garbleQueryInterceptor;
    }


}
