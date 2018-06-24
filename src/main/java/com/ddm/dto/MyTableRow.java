package com.ddm.dto;

import java.util.*;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
public class MyTableRow implements java.io.Serializable {

    public static String THE_TABLE_NAME_KEY = "__THE_TABLE_NAME_KEY__";
    private String tableName;
    private Map<String, Object> mapData; //for all CURD, if value contains '_' or '%' use like.

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, Object> getMapData() {
        return mapData;
    }

    public void setMapData(Map<String, Object> mapData) {
        this.mapData = mapData;
    }

    public Map<String, Object> toMybatisParamMap() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.putAll(this.mapData);
        paramMap.put(THE_TABLE_NAME_KEY, this.tableName);
        return paramMap;
    }

}
