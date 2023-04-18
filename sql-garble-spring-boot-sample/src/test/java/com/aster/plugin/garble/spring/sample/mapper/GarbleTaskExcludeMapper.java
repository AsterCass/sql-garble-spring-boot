package com.aster.plugin.garble.spring.sample.mapper;

import com.aster.plugin.garble.spring.sample.model.GarbleTask;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface GarbleTaskExcludeMapper extends Mapper<GarbleTask> {


    @SuppressWarnings("all")
    @Select("select * from garble_else.garble_task")
    List<GarbleTask> getAllElseTask();

    @Select("select * from garble_task where t_name = '工作xx'")
    List<GarbleTask> getCallChangeTaskCol();

    @Update("update garble_task set t_name =#{name} where id = #{id}")
    void updateOneCallback(@Param("id") Long id, @Param("name") String name);

    @Update("update garble_employee set e_msg =#{msg} where id = #{id}")
    void updateEmpCallback(@Param("id") Long id, @Param("msg") String msg);


}
