package com.aster.plugin.garble.spring.sample;

import com.aster.plugin.garble.spring.sample.mapper.GarbleCompanyMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleEmployeeMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskExcludeMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskMapper;
import com.aster.plugin.garble.spring.sample.model.GarbleCompany;
import com.aster.plugin.garble.spring.sample.model.GarbleEmployee;
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
@ActiveProfiles("select")
public class AuthSelectTest {

    @Resource
    private GarbleTaskMapper garbleTaskMapper;

    @Resource
    private GarbleTaskExcludeMapper garbleTaskExcludeMapper;

    @Resource
    private GarbleEmployeeMapper garbleEmployeeMapper;

    @Resource
    private GarbleCompanyMapper garbleCompanyMapper;

    @Test
    public void simpleAuthSelectTest() {
        log.info("[op:simpleAuthSelectTest] start");
        //test for "monitored-table-list" and "default-auth-strategy"
        // and "default-auth-col-name" and "monitored-table-list" and interface AuthenticationCodeInterface
        {
            List<GarbleTask> allAuthTaskList = garbleTaskMapper.selectAll();
            Assert.assertNotNull(allAuthTaskList);
            Assert.assertEquals(allAuthTaskList.size(), 20);
            List<GarbleCompany> allCompanyList = garbleCompanyMapper.selectAll();
            Assert.assertNotNull(allCompanyList);
            Assert.assertEquals(allCompanyList.size(), 6);
        }
        //test for "excluded-mapper-path"
        // and interface AuthenticationCodeInterface.@AuthenticationCodeBuilder.tables
        {
            List<GarbleTask> allTaskList = garbleTaskExcludeMapper.selectAll();
            Assert.assertNotNull(allTaskList);
            Assert.assertEquals(allTaskList.size(), 24);
            List<GarbleTask> allAuthTaskList = garbleTaskMapper.selectAll();
            Assert.assertNotNull(allAuthTaskList);
            Assert.assertEquals(allAuthTaskList.size(), 20);
        }
        //test for "monitored-table-list" and diff schema
        {
            List<GarbleTask> allElseTaskList = garbleTaskExcludeMapper.getAllElseTask();
            Assert.assertNotNull(allElseTaskList);
            Assert.assertEquals(allElseTaskList.size(), 24);
            List<GarbleTask> allAuthElseTaskList = garbleTaskMapper.getAllElseTask();
            Assert.assertNotNull(allAuthElseTaskList);
            Assert.assertEquals(allAuthElseTaskList.size(), 19);
        }
        //test for "monitored-table-auth-col-map" and "monitored-table-auth-strategy-map"
        //and diff interface AuthenticationCodeInterface
        {
            List<GarbleTask> allAuthTaskList = garbleTaskMapper.selectAll();
            Assert.assertNotNull(allAuthTaskList);
            Assert.assertEquals(allAuthTaskList.size(), 20);
            List<GarbleEmployee> allAuthEmployeeList = garbleEmployeeMapper.selectAll();
            Assert.assertNotNull(allAuthEmployeeList);
            Assert.assertEquals(allAuthEmployeeList.size(), 4);
        }
        //test for complex join select
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
        log.info("[op:simpleAuthSelectTest] end");

    }

    @Test
    public void complexAuthSelectTest() {
        log.info("[op:complexAuthSelectTest] start");
        //test for complex select
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
            List<GarbleTask> taskListChild = garbleEmployeeMapper.childSelect();
            Assert.assertNotNull(taskListChild);
            Assert.assertEquals(taskListChild.size(), 5);
        }
        log.info("[op:complexAuthSelectTest] end");
    }

    @Test
    public void elseTest() {
        log.info("[op:elseTest] this is else test");
    }

}
