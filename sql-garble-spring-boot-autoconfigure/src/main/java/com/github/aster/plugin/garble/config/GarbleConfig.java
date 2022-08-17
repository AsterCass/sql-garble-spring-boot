package com.github.aster.plugin.garble.config;

import com.github.aster.plugin.garble.enums.GarbleFunctionEnum;
import com.github.aster.plugin.garble.util.BeanMapUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置项需要有默认值，作为 properties put 的时候，当 value 为 null 时会报一个不好定位的空指针异常
 */
@Data
@ConfigurationProperties(GarbleConfig.GARBLE_PREFIX)
public class GarbleConfig {

    public static final String GARBLE_PREFIX = "garble";

    public Map<String, Object> getPropertiesMap() {
        return BeanMapUtil.beanToMap(this, GarbleConfig.class);
    }

    private Boolean valid = true;

    /**
     * 监控表和监控返回字段的Map，一般为主键，("user", "id")
     */
    private HashMap<String, String> monitoredTableMap = new HashMap<>();

    /**
     * 监控表和更新标记字段Map ("user", "update_record")
     */
    private HashMap<String, String> monitoredTableUpdateFlagColMap = new HashMap<String, String>();

    /**
     * 默认更新标记字段，如果监控表无法在更新标记字段Map中取得，则会使用默认更新标记字段
     */
    private String defaultFlagColName = "";

    /**
     * 不拦截的sql的路径
     */
    private List<String> excludedMapperPath = new ArrayList<>();

    /**
     * 需要的功能
     * {@link GarbleFunctionEnum}
     */
    List<Integer> garbleFunctionList;



}
