package com.aster.plugin.garble.spring.sample.mapper;

import com.aster.plugin.garble.spring.sample.model.GarbleTask;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface GarbleTaskMapper extends Mapper<GarbleTask> {


    @SuppressWarnings("all")
    @Select("select * from garble_else.garble_task")
    List<GarbleTask> getAllElseTask();

    @Update("update garble.garble_task gt set gt.t_name = '工作xx' where gt.e_id = 55")
    void updateOne();

    @Update("update garble_task set t_name = '工作xx' where e_id = 22")
    void updateMulti();

    /**
     * 目前不支持
     */
    @Update("update garble_task gt, garble_employee ge set gt.t_name = '工作xx', ge.e_msg='xxx' " +
            "where ge.id = gt.e_id and gt.e_id = 22")
    void updateMultiTab();

    @Update("update garble_task join garble_employee ge on garble_task.e_id = ge.id " +
            "set garble_task.t_name = '工作xx' where ge.e_msg = 'qb'")
    void updateJoin();


    @Update("update garble_task gt set gt.t_name = '工作xx' " +
            "where gt.e_id in (select id from garble_employee where e_msg = 'qb')")
    void updateChild();


}
