package com.aster.plugin.garble.spring.sample.mapper;

import com.aster.plugin.garble.spring.sample.bean.GarbleEmployee;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface GarbleEmployeeMapper extends Mapper<GarbleEmployee> {

}
