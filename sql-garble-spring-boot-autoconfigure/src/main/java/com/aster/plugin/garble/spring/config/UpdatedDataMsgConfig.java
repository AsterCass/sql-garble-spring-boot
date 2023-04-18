package com.aster.plugin.garble.spring.config;

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
@ConfigurationProperties(UpdatedDataMsgConfig.UPDATED_DATA_MSG)
public class UpdatedDataMsgConfig {

    public static final String UPDATED_DATA_MSG = "garble.updated-data-msg";

    public Map<String, Object> getPropertiesMap() {
        return BeanMapUtil.beanToMap(this, UpdatedDataMsgConfig.class);
    }

    /**
     * 标记实现DealWithUpdatedInterface接口的方法路径，加快初始化速度，可以不赋值
     */
    private String dealWithUpdatedPath = "";

    /**
     * 监控表和监控返回字段的Map，一般为主键，("user", "id")
     */
    private Map<String, String> monitoredTableMap = new HashMap<>();

    /**
     * 监控表和更新标记字段Map ("user", "update_record")
     */
    private Map<String, String> monitoredTableUpdateFlagColMap = new HashMap<String, String>();

    /**
     * 默认更新标记字段，如果监控表无法在更新标记字段Map中取得，则会使用默认更新标记字段
     */
    private String defaultFlagColName = "";

    /**
     * 不拦截的sql的路径
     */
    private List<String> excludedMapperPath = new ArrayList<>();

}
