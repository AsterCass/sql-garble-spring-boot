package com.github.aster.plugin.garble.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Properties;

@Data
@ConfigurationProperties(GarbleConfig.GARBLE_PREFIX)
public class GarbleConfig {

    public static final String GARBLE_PREFIX = "garble";

    private Properties properties = new Properties();

    private List<String> monitorTableList;

}
