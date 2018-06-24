package com.ddm.controller;

import com.ddm.dto.MyTableRow;
import com.ddm.service.SqlService;
import com.ddm.utils.DbOperDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
@Controller
@RequestMapping(value = "/sql")
public class SqlController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    private SqlService sqlService;

    @ResponseBody
    @PostMapping("/x")
    List<Map<String, ?>> runSql(String sql) {
        return sqlService.runSql(sql);
    }


    @PostMapping
    @ResponseBody
    public int create(@RequestBody MyTableRow tableRow) {
        return sqlService.create(tableRow);
    }

    @GetMapping
    @ResponseBody
    public List<Map<String, ?>> retrieve(@RequestParam String tableName, @RequestParam(required = false) Long ID, HttpServletRequest req) {
        MyTableRow tableRow = new MyTableRow();
        Map<String, Object> mapData = new HashMap<>();
        Enumeration em = req.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = req.getParameter(name);
            if ("tableName".equals(name)) {
                tableName = value;
                continue;
            }
            mapData.put(name, value);
        }
        tableRow.setTableName(tableName);
        tableRow.setMapData(mapData);
        return sqlService.retrieve(tableRow);
    }

    @PutMapping
    @ResponseBody
    public int update(@RequestBody MyTableRow tableRow) {
        return sqlService.update(tableRow);
    }

    @DeleteMapping
    @ResponseBody
    public int delete(@RequestBody MyTableRow tableRow) {
        return sqlService.delete(tableRow);
    }




    //Use tableName as pathvariable
    @PostMapping(path = "/{tableName}")
    @ResponseBody
    public int createx(@RequestBody Map<String, Object> mapData, @PathVariable("tableName") String tableName, @RequestParam(required = false, defaultValue = "false") boolean createTable) {
        MyTableRow tableRow = new MyTableRow();
        tableRow.setTableName(tableName);
        tableRow.setMapData(mapData);
        if (createTable) {
            String createTableSql = DbOperDo.getCreateTableByDat(tableName, mapData);
            sqlService.runSql(createTableSql);
        }
        return sqlService.create(tableRow);
    }

    @GetMapping(path = "/{tableName}")
    @ResponseBody
    public List<Map<String, ?>> retrievex(@RequestParam(required = false) Long ID, @PathVariable("tableName") String tableName, HttpServletRequest req) {
        MyTableRow tableRow = new MyTableRow();
        Map<String, Object> mapData = new HashMap<>();
        Enumeration em = req.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = req.getParameter(name);
            if ("tableName".equals(name)) {
                tableName = value;
                continue;
            }
            mapData.put(name, value);
        }
        tableRow.setTableName(tableName);
        tableRow.setMapData(mapData);
        return sqlService.retrieve(tableRow);
    }

    @PutMapping(path = "/{tableName}")
    @ResponseBody
    public int updatex(@RequestBody Map<String, Object> mapData, @PathVariable("tableName") String tableName) {
        MyTableRow tableRow = new MyTableRow();
        tableRow.setTableName(tableName);
        tableRow.setMapData(mapData);
        return sqlService.update(tableRow);
    }

    @DeleteMapping(path = "/{tableName}")
    @ResponseBody
    public int deletex(@RequestBody Map<String, Object> mapData, @PathVariable("tableName") String tableName) {
        MyTableRow tableRow = new MyTableRow();
        tableRow.setTableName(tableName);
        tableRow.setMapData(mapData);
        return sqlService.delete(tableRow);
    }

}
