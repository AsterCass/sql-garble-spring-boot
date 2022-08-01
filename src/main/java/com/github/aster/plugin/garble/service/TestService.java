package com.github.aster.plugin.garble.service;


import com.github.aster.plugin.garble.config.TestConfig;

import javax.annotation.Resource;

public class TestService {

    @Resource
    TestConfig testConfig;

    public String sayHello(String userName) {
        return testConfig.getPrefix() + userName + testConfig.getSuffix();
    }


}
