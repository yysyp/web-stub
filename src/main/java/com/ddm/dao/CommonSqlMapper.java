package com.ddm.dao;

import com.ddm.dto.MyTableRow;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
//@Mapper
public interface CommonSqlMapper {

//    static class PureSqlProvider {
//        public String sql(String sql) {
//            return sql;
//        }
//
//        public String count(String from) {
//            return "SELECT count(*) FROM " + from;
//        }
//    }

    @SelectProvider(type = PureSqlProvider.class, method = "sql")
    //@ResultType(java.util.HashMap.class)
    public List<Map<String, ?>> runSql(String sql);


    @InsertProvider(type = PureSqlProvider.class, method = "insert")
    public int insert(Map<String, Object> param);

    @SelectProvider(type = PureSqlProvider.class, method = "select")
    public List<Map<String, ?>> select(Map<String, Object> param);

    @UpdateProvider(type = PureSqlProvider.class, method = "update")
    public int update(Map<String, Object> param);

    @DeleteProvider(type = PureSqlProvider.class, method = "delete")
    public int delete(Map<String, Object> param);

}
