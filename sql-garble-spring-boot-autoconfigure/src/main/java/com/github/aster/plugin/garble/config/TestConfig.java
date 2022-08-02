package com.github.aster.plugin.garble.config;

import com.github.aster.plugin.garble.dto.TestDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties("com.github.aster.test")
public class TestConfig {

    private List<String> dataList;

    private Map<String, String> dataMap;

    private List<TestDto> dataDtoList;

    private String dataString;

}
