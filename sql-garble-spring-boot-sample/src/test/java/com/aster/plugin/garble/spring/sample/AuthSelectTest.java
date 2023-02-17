package com.aster.plugin.garble.spring.sample;

import com.aster.plugin.garble.spring.sample.bean.GarbleTask;
import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthSelectTest {

    @Resource
    private GarbleTaskMapper garbleTaskMapper;

    @Test
    public void simpleTest() {
        List<GarbleTask> allGarbleList = garbleTaskMapper.selectAll();
        log.info("1111");
    }

    @Test
    public void elseTest() {
        log.info("[op:elseTest] this is else test");
    }

}
