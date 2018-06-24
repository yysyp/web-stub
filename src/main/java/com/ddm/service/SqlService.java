package com.ddm.service;

import com.ddm.dto.MyTableRow;

import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
public interface SqlService {

    List<Map<String, ?>> runSql(String sql);

    int create(MyTableRow tableRow);

    List<Map<String, ?>> retrieve(MyTableRow tableRow);

    int update(MyTableRow tableRow);

    int delete(MyTableRow tableRow);

}
