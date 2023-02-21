package com.aster.plugin.garble.spring.sample;

import com.aster.plugin.garble.spring.sample.bean.GarbleEmployee;
import com.aster.plugin.garble.spring.sample.bean.GarbleTask;
import com.aster.plugin.garble.spring.sample.mapper.GarbleEmployeeMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskExcludeMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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

    @Resource
    private GarbleTaskExcludeMapper garbleTaskExcludeMapper;

    @Resource
    private GarbleEmployeeMapper garbleEmployeeMapper;

    @Test
    public void simpleAuthSelectTest() {
        log.info("[op:simpleAuthSelectTest] start");
        //base task
        {
            List<GarbleTask> allTaskList = garbleTaskExcludeMapper.selectAll();
            Assert.assertNotNull(allTaskList);
            Assert.assertEquals(allTaskList.size(), 24);
            List<GarbleTask> allAuthTaskList = garbleTaskMapper.selectAll();
            Assert.assertNotNull(allAuthTaskList);
            Assert.assertEquals(allAuthTaskList.size(), 21);
        }
        //else task
        {
            List<GarbleTask> allElseTaskList = garbleTaskExcludeMapper.getAllElseTask();
            Assert.assertNotNull(allElseTaskList);
            Assert.assertEquals(allElseTaskList.size(), 24);
            List<GarbleTask> allAuthElseTaskList = garbleTaskMapper.getAllElseTask();
            Assert.assertNotNull(allAuthElseTaskList);
            Assert.assertEquals(allAuthElseTaskList.size(), 18);
        }
        //base employ
        {
            List<GarbleEmployee> allAuthEmployeeList = garbleEmployeeMapper.selectAll();
            Assert.assertNotNull(allAuthEmployeeList);
            Assert.assertEquals(allAuthEmployeeList.size(), 4);
        }

        log.info("[op:simpleAuthSelectTest] end");

    }

    @Test
    public void elseTest() {
        log.info("[op:elseTest] this is else test");
    }

}
