package com.github.aster.plugin.garble.config;

import com.github.aster.plugin.garble.util.BeanMapUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
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


    private List<String> monitorTableList = Arrays.asList(
            "hr_house_pr", "hr_house", "hr_room", "hr_address");

    private HashMap<String, String> monitorPrimaryKeyMap = new HashMap<String, String>() {{
        put("hr_house_pr", "id");
        put("hr_house", "id");
        put("hr_address", "id");
        put("hr_room", "id");
    }};

    private String monitorExcludeMapperPath = "com.qmrz.apply.mapper.MonitorExcludeMapper";

    private String test = "test";


}
