package com.aster.plugin.garble.spring.sample;

import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskExcludeMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UpdateCallbackTest {

    @Resource
    private GarbleTaskMapper garbleTaskMapper;

    @Resource
    private GarbleTaskExcludeMapper garbleTaskExcludeMapper;

    @Test
    public void simpleUpdateCallbackTest() {
        log.info("[op:simpleUpdateCallbackTest] start");
        //base
        {
            garbleTaskMapper.updateOne();
            garbleTaskExcludeMapper.updateOneCallback();
        }

        log.info("[op:simpleUpdateCallbackTest] start");
    }

    @Test
    public void elseTest() {
        log.info("[op:elseTest] this is else test");
    }

}
