package com.aster.plugin.garble.spring.sample;

import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskExcludeMapper;
import com.aster.plugin.garble.spring.sample.mapper.GarbleTaskMapper;
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
@ActiveProfiles("call")
public class UpdateCallbackTest {

    @Resource
    private GarbleTaskMapper garbleTaskMapper;

    @Resource
    private GarbleTaskExcludeMapper garbleTaskExcludeMapper;

    @Test
    public void simpleUpdateCallbackTest() {
        log.info("[op:simpleUpdateCallbackTest] start");
        //test update one col
        //{"garble_task":["7"]}
        {
            log.info("[op:simpleUpdateCallbackTest] call back {\"garble_task\":[\"7\"]}");
            garbleTaskMapper.updateOne();
            List<GarbleTask> taskList = garbleTaskExcludeMapper.getCallChangeTaskCol();
            Assert.assertNotNull(taskList);
            Assert.assertEquals(taskList.size(), 1);
            garbleTaskExcludeMapper.updateOneCallback(7L, "工作7");
        }
        //test for update multi col
        //{"garble_task":["4","5"]}
        {
            log.info("[op:simpleUpdateCallbackTest] call back {\"garble_task\":[\"4\",\"5\"]}");
            garbleTaskMapper.updateMulti();
            List<GarbleTask> taskList = garbleTaskExcludeMapper.getCallChangeTaskCol();
            Assert.assertNotNull(taskList);
            Assert.assertEquals(taskList.size(), 2);
            garbleTaskExcludeMapper.updateOneCallback(4L, "工作4");
            garbleTaskExcludeMapper.updateOneCallback(5L, "工作5");
        }
        //test for update multi table
        //{"garble_employee":["22"],"garble_task":["4","5"]}
        {
            log.info("[op:simpleUpdateCallbackTest] " +
                    "call back {\"garble_employee\":[\"22\"],\"garble_task\":[\"4\",\"5\"]}");
            garbleTaskMapper.updateMultiTab();
            garbleTaskExcludeMapper.updateOneCallback(4L, "工作4");
            garbleTaskExcludeMapper.updateOneCallback(5L, "工作5");
            garbleTaskExcludeMapper.updateEmpCallback(22L, "qb");
        }
        //test for tk.mapper sql
        //{"garble_task":["4","5"]}
        {
            log.info("[op:simpleUpdateCallbackTest] call back {\"garble_task\":[\"4\",\"5\"]}");
            Example simpleEx = new Example(GarbleTask.class);
            simpleEx.and().andEqualTo("eId", 22);
            GarbleTask newGarbleT = new GarbleTask();
            newGarbleT.setTName("工作xx");
            garbleTaskMapper.updateByExampleSelective(newGarbleT, simpleEx);
            //check
            List<GarbleTask> taskList = garbleTaskExcludeMapper.getCallChangeTaskCol();
            Assert.assertNotNull(taskList);
            Assert.assertEquals(taskList.size(), 2);
            //roll back
            garbleTaskExcludeMapper.updateOneCallback(4L, "工作4");
            garbleTaskExcludeMapper.updateOneCallback(5L, "工作5");
        }
        log.info("[op:simpleUpdateCallbackTest] start");
    }

    @Test
    public void complexUpdateCallbackTest() {
        //test for join
        //{"garble_employee":["22"],"garble_task":["4","5"]}
        {
            log.info("[op:complexUpdateCallbackTest] " +
                    "call back {\"garble_employee\":[\"22\"],\"garble_task\":[\"4\",\"5\"]}}");
            garbleTaskMapper.updateJoin();
            List<GarbleTask> taskList = garbleTaskExcludeMapper.getCallChangeTaskCol();
            Assert.assertNotNull(taskList);
            Assert.assertEquals(taskList.size(), 2);
            garbleTaskExcludeMapper.updateOneCallback(4L, "工作4");
            garbleTaskExcludeMapper.updateOneCallback(5L, "工作5");
        }
        //test for child
        //{"garble_task":["4","5"]}
        {
            log.info("[op:complexUpdateCallbackTest] call back {\"garble_task\":[\"4\",\"5\"]}");
            garbleTaskMapper.updateChild();
            List<GarbleTask> taskList = garbleTaskExcludeMapper.getCallChangeTaskCol();
            Assert.assertNotNull(taskList);
            Assert.assertEquals(taskList.size(), 2);
            garbleTaskExcludeMapper.updateOneCallback(4L, "工作4");
            garbleTaskExcludeMapper.updateOneCallback(5L, "工作5");
        }
    }

    @Test
    public void elseTest() {
        log.info("[op:elseTest] this is else test");
    }

}
