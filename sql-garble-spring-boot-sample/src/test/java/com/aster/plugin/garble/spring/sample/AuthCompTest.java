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
import tk.mybatis.mapper.entity.Example;

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
        {
            //update
            Example example = new Example(GarbleTask.class);
            example.and().andEqualTo("eId", 88);
            GarbleTask garbleTask = new GarbleTask();
            garbleTask.setTName("工作100");
            garbleTaskMapper.updateByExampleSelective(garbleTask, example);
            //roll back and check
            int colNum = garbleTaskExcludeMapper.updateAuthRollback();
            Assert.assertEquals(colNum, 2);
        }
        {
            //update else
            garbleTaskMapper.updateAuthElseTask();
            //roll back and check
            int colNum = garbleTaskExcludeMapper.updateAuthElseTaskRollback();
            Assert.assertEquals(colNum, 1);
        }
        {
            //update multi
            garbleTaskMapper.updateAuthMultiTab();
            //result check
            Example exampleGt = new Example(GarbleTask.class);
            exampleGt.and().andEqualTo("tName", "工作xx");
            List<GarbleTask> gtList = garbleTaskExcludeMapper.selectByExample(exampleGt);
            Assert.assertNotNull(gtList);
            Assert.assertEquals(gtList.size(), 2);
            List<GarbleEmployee> geList = garbleTaskExcludeMapper.updateAuthMultiTabCheck();
            Assert.assertNotNull(geList);
            Assert.assertEquals(geList.size(), 1);
            //roll back
            garbleTaskExcludeMapper.updateOneCallback(14L, "工作10");
            garbleTaskExcludeMapper.updateOneCallback(15L, "工作10");
            garbleTaskExcludeMapper.updateEmpCallback(88L, "ww");
        }

        log.info("[op:simpleAuthCompTest] end");
    }
}
