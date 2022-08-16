package com.github.aster.plugin.garble.config;

import com.github.aster.plugin.garble.util.BeanMapUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;

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

    private HashMap<String, String> monitoredTableMap = new HashMap<>();

    private HashMap<String, String> monitoredTableUpdateFlagColMap = new HashMap<String, String>();

    private String defaultFlagColName = "";

    private List<String> excludedMapperPath = new ArrayList<>();



}
