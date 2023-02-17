package com.aster.plugin.garble.spring.sample.call;

import com.alibaba.fastjson.JSON;
import com.aster.plugin.garble.enums.AuthenticationStrategyEnum;
import com.aster.plugin.garble.service.AuthenticationCodeBuilder;
import com.aster.plugin.garble.service.AuthenticationCodeInterface;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthTaskService implements AuthenticationCodeInterface {


    /**
     * 后去权限code，用于和配置字段相比较
     * {@link AuthenticationStrategyEnum}
     * <p>
     * 如果使用的是AuthenticationStrategyEnum.BOOLEAN_AND需要传入的为纯数字的字符串
     * <p>
     * 如果使用的是AuthenticationStrategyEnum.INTERSECTION 需要传入的为可解析的ListString类型的字符串,
     * 数据需要为ListString或者单独的String字符串
     * <p>
     * 方法将会在查询监控表的时候依据指定的及authentication strategy，根据此方法的的返回值进行鉴权
     *
     * @return 鉴权code
     */
    @Override
    @AuthenticationCodeBuilder(type = 2, tables = {"^.*garble_task$"})
    public String authenticationCodeBuilder() {
        return JSON.toJSONString(Collections.singletonList("123"));
    }
}
