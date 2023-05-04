package com.aster.plugin.garble.spring.sample;

import com.aster.plugin.garble.spring.sample.mapper.GarbleCompanyMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleEmployeeMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskExcludeMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskMapper;
import com.aster.plugin.garble.spring.sample.model.GarbleCompany;
import com.aster.plugin.garble.spring.sample.model.GarbleTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("comp")
public class AuthCompTest {

    @Resource
    private GarbleTaskMapper garbleTaskMapper;

    @Resource
    private GarbleTaskExcludeMapper garbleTaskExcludeMapper;

    @Resource
    private GarbleEmployeeMapper garbleEmployeeMapper;

    @Resource
    private GarbleCompanyMapper garbleCompanyMapper;

    @Test
    public void simpleAuthCompTest() {
        log.info("[op:simpleAuthCompTest] start");
        {
            List<GarbleTask> allAuthTaskList = garbleTaskMapper.selectAll();
            Assert.assertNotNull(allAuthTaskList);
            Assert.assertEquals(allAuthTaskList.size(), 20);
            List<GarbleCompany> allCompanyList = garbleCompanyMapper.selectAll();
            Assert.assertNotNull(allCompanyList);
            Assert.assertEquals(allCompanyList.size(), 6);
        }
        {
            List<GarbleTask> allElseTaskList = garbleTaskExcludeMapper.getAllElseTask();
            Assert.assertNotNull(allElseTaskList);
            Assert.assertEquals(allElseTaskList.size(), 24);
            List<GarbleTask> allAuthElseTaskList = garbleTaskMapper.getAllElseTask();
            Assert.assertNotNull(allAuthElseTaskList);
            Assert.assertEquals(allAuthElseTaskList.size(), 19);
        }
        {
            List<GarbleTask> taskList = garbleEmployeeMapper.joinSelect();
            Assert.assertNotNull(taskList);
            Assert.assertEquals(taskList.size(), 5);
            List<GarbleTask> taskListCondition = garbleEmployeeMapper.joinSelectCondition();
            Assert.assertNotNull(taskListCondition);
            Assert.assertEquals(taskListCondition.size(), 2);
            List<GarbleTask> taskListElseSchema = garbleEmployeeMapper.joinSelectElseSchema();
            Assert.assertNotNull(taskListElseSchema);
            Assert.assertEquals(taskListElseSchema.size(), 1);
        }
        log.info("[op:simpleAuthCompTest] end");
    }
}
