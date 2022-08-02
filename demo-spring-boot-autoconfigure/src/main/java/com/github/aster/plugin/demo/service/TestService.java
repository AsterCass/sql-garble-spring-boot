package com.github.aster.plugin.demo.service;


import com.github.aster.plugin.demo.config.TestConfig;

import javax.annotation.Resource;

public class TestService {

    @Resource
    TestConfig testConfig;

    public String sayHello(String userName) {
        return testConfig.getPrefix() + userName + testConfig.getSuffix();
    }


}
