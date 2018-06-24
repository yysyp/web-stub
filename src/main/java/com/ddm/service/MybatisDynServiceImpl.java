package com.ddm.service;


import com.ddm.dao.MybatisDynServiceMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
@Service(value ="mybatisDynService")
public class MybatisDynServiceImpl implements  MybatisDynService {

    @Autowired
    private MybatisDynServiceMapper mybatisDynServiceMapper;

    public int insertData(List<Map<String,Object>> insertItems, String tableName) {
        int insertedCount = 0;
        if (!insertItems.isEmpty()) {
            Map<String,Object> params = Maps.newHashMap();
            int count = insertItems.size() / 1000;
            int yu = insertItems.size() % 1000;
            for (int i = 0; i <= count; i++) {
                List<Map<String,Object>> subList = Lists.newArrayList();
                if (i == count) {
                    if(yu != 0){
                        subList = insertItems.subList(i * 1000, 1000 * i + yu);
                    }else {
                        continue;
                    }
                } else {
                    subList = insertItems.subList(i * 1000, 1000 * (i + 1));
                }
                params.put("table_name", tableName);
                params.put("fields", subList.get(0));
                params.put("list", subList);
                insertedCount += mybatisDynServiceMapper.insertData(params);
            }
        }
        return insertedCount;
    }

}
