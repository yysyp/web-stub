package com.ddm.service;

import com.ddm.dao.CommonSqlMapper;
import com.ddm.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
@Service(value= "sqlService")
public class SqlServiceImpl implements SqlService {

    @Autowired
    private CommonSqlMapper commonSqlMapper;

    @Override
    public List<Map<String, ?>> runSql(String sql) {
        return commonSqlMapper.select(sql);
    }
}