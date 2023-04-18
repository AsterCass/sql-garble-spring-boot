package com.aster.plugin.garble.spring.sample.mapper;

import com.aster.plugin.garble.spring.sample.model.GarbleEmployee;
import com.aster.plugin.garble.spring.sample.model.GarbleTask;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface GarbleEmployeeMapper extends Mapper<GarbleEmployee> {

    @Select("select gt.* from garble_employee ge join garble_task gt on ge.id = gt.e_id")
    List<GarbleTask> joinSelect();

    @Select("select gt.* from `garble_employee` join garble_task gt " +
            "on `garble_employee`.id = gt.e_id where gt.e_id = 11")
    List<GarbleTask> joinSelectCondition();

    @SuppressWarnings("all")
    @Select("select * from garble.garble_employee join garble_else.garble_task gt " +
            "on garble.garble_employee.id*10 = gt.e_id")
    List<GarbleTask> joinSelectElseSchema();

    @Select("select * from garble_task gt where gt.e_id in (select id from garble_employee)")
    List<GarbleTask> childSelect();

}
