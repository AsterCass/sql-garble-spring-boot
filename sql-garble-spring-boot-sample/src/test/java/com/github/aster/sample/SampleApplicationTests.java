package com.github.aster.sample;

import com.github.aster.plugin.garble.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class SampleApplicationTests {

    @Resource
    private TestService testService;

    @Test
    void contextLoads() {
        log.info(testService.getConfig());
    }

}
