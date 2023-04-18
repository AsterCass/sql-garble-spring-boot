package com.aster.plugin.garble.spring.config;

import com.aster.plugin.garble.enums.AuthenticationStrategyEnum;
import com.aster.plugin.garble.spring.util.BeanMapUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author astercasc
 */
@Data
@ConfigurationProperties(AuthSelectConfig.AUTH_SELECT_CONFIG)
public class AuthSelectConfig {

    public static final String AUTH_SELECT_CONFIG = "garble.auth.select";

    public Map<String, Object> getPropertiesMap() {
        return BeanMapUtil.beanToMap(this, AuthSelectConfig.class);
    }

    /**
     * 标记实现AuthenticationCodeInterface接口的方法路径，加快加快初始化速度，可以不赋值
     */
    private String authCodePath = "";

    /**
     * 监控表列表
     */
    private List<String> monitoredTableList = new ArrayList<>();

    /**
     * 监控表和权限标记列
     */
    private Map<String, String> monitoredTableAuthColMap = new HashMap<>();

    /**
     * 监控表的默认权限标记列，当monitoredTableUpdateFlagColMap无法查询到需要监控表的权限标记列的时候，使用默认权限标记列
     */
    private String defaultAuthColName = "";

    /**
     * 监控表和权限策略
     * {@link AuthenticationStrategyEnum}
     */
    private Map<String, Integer> monitoredTableAuthStrategyMap = new HashMap<>();

    /**
     * 监控表和权限策略，当monitoredTableAuthStrategyMap无法查询到需要监控表的权限策略的时候，使用默认权限测率
     * {@link AuthenticationStrategyEnum}
     */
    private Integer defaultAuthStrategy = -1;

    /**
     * 在此map中的的sql不受到监控，即使包含监控表
     */
    private List<String> excludedMapperPath = new ArrayList<>();

}
