package com.aster.plugin.garble.spring.sample;

import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskExcludeMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskMapper;
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
@ActiveProfiles("update")
public class AuthUpdateTest {

    @Resource
    private GarbleTaskMapper garbleTaskMapper;

    @Resource
    private GarbleTaskExcludeMapper garbleTaskExcludeMapper;


    @Test
    public void simpleAuthUpdateTest() {
        log.info("[op:simpleAuthUpdateTest] start");
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
            //update else
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
        {
            //update join
            garbleTaskMapper.updateAuthJoin();
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
        {
            //update child
            //todo 这里会读取update配置到子查询的select中 参考read中【挂起的功能】的描述，
            //todo 但是一般更新鉴权和查询鉴权配置都是相同的 这个问题的优先级不高
            garbleTaskMapper.updateAuthChild();
            //result check
            Example exampleGt = new Example(GarbleTask.class);
            exampleGt.and().andEqualTo("tName", "工作xx");
            List<GarbleTask> gtList = garbleTaskExcludeMapper.selectByExample(exampleGt);
            Assert.assertNotNull(gtList);
            Assert.assertEquals(gtList.size(), 2);
            //roll back
            garbleTaskExcludeMapper.updateOneCallback(14L, "工作10");
            garbleTaskExcludeMapper.updateOneCallback(15L, "工作10");
        }
        log.info("[op:simpleAuthUpdateTest] end");
    }
}
