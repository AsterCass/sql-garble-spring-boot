package com.github.aster.plugin.demo.service;


import com.alibaba.fastjson.JSON;
import com.github.aster.plugin.demo.config.TestConfig;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
public class TestService {

    @Resource
    TestConfig testConfig;

    public String getConfig() {
        return testConfig.getDataString() + "\n"
                + JSON.toJSONString(testConfig.getDataDtoList()) + "\n"
                + JSON.toJSONString(testConfig.getDataList()) + "\n"
                + JSON.toJSONString(testConfig.getDataMap());
    }


}
