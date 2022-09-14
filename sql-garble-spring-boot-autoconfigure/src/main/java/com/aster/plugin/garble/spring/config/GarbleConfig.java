package com.aster.plugin.garble.spring.config;

import com.aster.plugin.garble.enums.GarbleFunctionEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 配置项需要有默认值，作为 properties put 的时候，当 value 为 null 时会报一个不好定位的空指针异常
 * 我们允许配置项不填写
 * @author astercasc
 */
@Data
@ConfigurationProperties(GarbleConfig.GARBLE_PREFIX)
public class GarbleConfig {

    public static final String GARBLE_PREFIX = "garble";

    /**
     * 是否生效
     */
    private Boolean valid = true;

    /**
     * 需要的功能
     * {@link GarbleFunctionEnum}
     */
    private List<Integer> garbleFunctionList;



}
