package com.aster.plugin.garble.spring.config;

import com.aster.plugin.garble.spring.util.BeanMapUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author astercasc
 */
@Data
@ConfigurationProperties(AuthSelectConfig.AUTH_SELECT_CONFIG)
public class AuthSelectConfig {

    public static final String AUTH_SELECT_CONFIG = "garble.auth.select";

    public Map<String, Object> getPropertiesMap() {
        return BeanMapUtil.beanToMap(this, GarbleConfig.class);
    }

    /**
     * 标记实现DealWithUpdatedInterface接口的方法路径，加快初始化速度，可以不赋值
     */
    private String authCodeBuilderPath = "";

}
