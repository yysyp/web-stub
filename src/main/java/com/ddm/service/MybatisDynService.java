package com.ddm.service;

import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
public interface MybatisDynService {
    public int insertData(List<Map<String,Object>> insertItems, String tableName);
}
