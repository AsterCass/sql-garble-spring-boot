package com.aster.plugin.garble.spring.sample.mapper;

import com.aster.plugin.garble.spring.sample.bean.GarbleTask;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface GarbleTaskExcludeMapper extends Mapper<GarbleTask> {


    @SuppressWarnings("all")
    @Select("select * from garble_else.garble_task")
    List<GarbleTask> getAllElseTask();

    @Update("update garble_task set t_name = '工作7' where id = 7")
    void updateOneCallback();


}
