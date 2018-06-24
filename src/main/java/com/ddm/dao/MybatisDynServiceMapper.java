package com.ddm.dao;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
//@Mapper scan configured in configure, so here don't need anymore
public interface MybatisDynServiceMapper {

    public int insertData(Map<String,Object> params);

    public int updateData(Map<String,Object> params);
}
