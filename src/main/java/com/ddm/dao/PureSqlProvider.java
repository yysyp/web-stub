package com.ddm.dao;

import com.ddm.dto.MyTableRow;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/24/2018.
 */
public class PureSqlProvider {

    public static String ID = "ID";

    public String sql(String sql) {
        return sql;
    }


    public String insert(Map<String, Object> map) {
        String tableName = (String) map.remove(MyTableRow.THE_TABLE_NAME_KEY);
        StringBuilder insertSql = new StringBuilder();
        filterOutID(map);
        filterOutNullValue(map);
        insertSql.append("INSERT INTO ").append(tableName)
                .append("(").append(constructCommaKeys(map)).append(")")
                .append(" VALUES (").append(constructCommaKeysPound(map)).append(")");
        return insertSql.toString();
    }

    public String select(Map<String, Object> map) {
        String tableName = (String) map.remove(MyTableRow.THE_TABLE_NAME_KEY);
        StringBuilder selectSql = new StringBuilder();
        selectSql.append("SELECT ");
        if (map.isEmpty() || (map.size() == 1 && map.containsKey(ID))) {
            selectSql.append(" * ");
        } else {
            selectSql.append(constructCommaKeys(map));
        }
        selectSql.append(" FROM ").append(tableName);

        if (!map.isEmpty()) {
            filterOutNullValue(map);
            selectSql.append(constructWhere(map));
        }
        return selectSql.toString();
    }



    public String update(Map<String, Object> map) {
        String tableName = (String) map.remove(MyTableRow.THE_TABLE_NAME_KEY);
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE ").append(tableName).append(" SET ");

        Object id = map.get(ID);
        filterOutID(map);
        filterOutNullValue(map);
        sql.append(constructCommaKeysEqual(map));

        //Only use id as where condition for update.
        map.put(ID, id);
        sql.append(" WHERE ID = #{ID} ");

        return sql.toString();
    }

    public String delete(Map<String, Object> map) {
        String tableName = (String) map.remove(MyTableRow.THE_TABLE_NAME_KEY);
        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM ").append(tableName);

        if (!map.isEmpty()) {
            filterOutNullValue(map);
            sql.append(constructWhere(map));
        }

        return sql.toString();
    }

    public static void filterOutNullValue(Map<String, Object> map) {
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            if (entry.getValue() == null) {
                entries.remove();
            }
        }

    }

    public static void filterOutID(Map<String, Object> map) {
        map.remove(ID);
    }

    public String constructWhere(Map<String, Object> map) {
        StringBuilder sql = new StringBuilder();
        sql.append(" WHERE 1=1 ");
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, ?> entry = entries.next();
            sql.append(" AND ").append(entry.getKey());
            String value = entry.getValue()+"";
            if (value.contains("_") || value.contains("%")) {
                sql.append(" like ");
            } else {
                sql.append(" = ");
            }
            sql.append("#{").append(entry.getKey()).append("}");
        }
        return sql.toString();
    }

    public String constructCommaKeys(Map<String, Object> map) {
        return Joiner.on(", ").join(map.keySet());
    }

    public String constructCommaKeysPound(Map<String, Object> map) {
        return "#{"+Joiner.on("}, #{").join(map.keySet())+"}";
    }

    public String constructCommaKeysEqual(Map<String, Object> map) {
        StringBuilder sql = new StringBuilder();
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, ?> entry = entries.next();
            sql.append(entry.getKey()).append(" = #{").append(entry.getKey()).append("}");
            //String value = entry.getValue()+"";
            if (entries.hasNext()) {
                sql.append(", ");
            }
        }
        return sql.toString();
    }


}
