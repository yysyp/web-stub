package com.ddm.service;

import com.ddm.dao.CommonSqlMapper;
import com.ddm.dto.MyTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
@Service(value= "sqlService")
public class SqlServiceImpl implements SqlService {

    @Autowired
    private CommonSqlMapper commonSqlMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Map<String, ?>> runSql(String sql) {
        return commonSqlMapper.runSql(sql);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int create(MyTableRow tableRow) {
        return commonSqlMapper.insert(tableRow.toMybatisParamMap());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Map<String, ?>> retrieve(MyTableRow tableRow) {
        return commonSqlMapper.select(tableRow.toMybatisParamMap());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int update(MyTableRow tableRow) {
        return commonSqlMapper.update(tableRow.toMybatisParamMap());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delete(MyTableRow tableRow) {
        return commonSqlMapper.delete(tableRow.toMybatisParamMap());
    }
}
