package com.ddm.dao;

import org.apache.ibatis.annotations.SelectProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
//@Mapper
public interface CommonSqlMapper {

    static class PureSqlProvider {
        public String sql(String sql) {
            return sql;
        }

        public String count(String from) {
            return "SELECT count(*) FROM " + from;
        }
    }

    @SelectProvider(type = PureSqlProvider.class, method = "sql")
    //@ResultType(java.util.HashMap.class)
    public List<Map<String, ?>> select(String sql);

    @SelectProvider(type = PureSqlProvider.class, method = "count")
    public Integer count(String from);

    @SelectProvider(type = PureSqlProvider.class, method = "sql")
    public Integer execute(String query);

}
