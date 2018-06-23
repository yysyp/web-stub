package com.ddm.controller;

import com.ddm.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
@Controller
@RequestMapping(value = "/sql")
public class SqlController {

    @Autowired
    private SqlService sqlService;

    @ResponseBody
    @PostMapping("/run")
    List<Map<String, ?>> runSql(String sql) {
        return sqlService.runSql(sql);
    }

}
