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

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("insert")
public class AuthInsertTest {


    @Resource
    private GarbleTaskMapper garbleTaskMapper;

    @Resource
    private GarbleTaskExcludeMapper garbleTaskExcludeMapper;

    @Test
    public void simpleAuthInsertTest() {
        log.info("[op:simpleAuthInsertTest] start");
        {
            //insert sql
            garbleTaskMapper.insertAuthSimple(30L);
            //check
            Example example = new Example(GarbleTask.class);
            example.and().andEqualTo("authCodeCol", "1234");
            int count = garbleTaskExcludeMapper.selectCountByExample(example);
            Assert.assertEquals(count, 5);
            //roll back
            garbleTaskExcludeMapper.deleteByPrimaryKey(30L);
        }
        {
            //insert tk mybatis
            GarbleTask garbleTask = new GarbleTask();
            garbleTask.setId(30L);
            garbleTask.setTName("工作30x");
            garbleTask.setEId(3000L);
            garbleTaskMapper.insertSelective(garbleTask);
            //check
            Example example = new Example(GarbleTask.class);
            example.and().andEqualTo("authCodeCol", "1234");
            int count = garbleTaskExcludeMapper.selectCountByExample(example);
            Assert.assertEquals(count, 5);
            //roll back
            garbleTaskExcludeMapper.deleteByPrimaryKey(30L);
        }
        log.info("[op:simpleAuthInsertTest] end");
    }
}
