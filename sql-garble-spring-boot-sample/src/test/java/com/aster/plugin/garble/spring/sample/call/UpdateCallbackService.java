package com.aster.plugin.garble.spring.sample.call;

import com.alibaba.fastjson.JSON;
import com.aster.plugin.garble.service.DealWithUpdated;
import com.aster.plugin.garble.service.DealWithUpdatedInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UpdateCallbackService implements DealWithUpdatedInterface {


    /**
     * 更新的表和更新行的数据Map返回
     *
     * @param updatedTableMap 更新数据
     */
    @DealWithUpdated(priority = 1)
    @Override
    public void execute(Map<String, List<String>> updatedTableMap) {
        log.info(JSON.toJSONString(updatedTableMap));
    }

}
