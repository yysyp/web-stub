package com.ddm.controller;

import com.ddm.service.MybatisDynService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yunpeng.song on 6/23/2018.
 */
@Controller
@RequestMapping(value = "/mdc")
public class MybatisDynController {

    @Autowired
    private MybatisDynService mybatisDynService;


    @PostMapping
    @ResponseBody
    public List<Map<String, ?>> create(String sql) {
        return null;
    }

    @GetMapping
    @ResponseBody
    public List<Map<String, ?>> retrieve(String sql) {
        return null;
    }

    @PutMapping
    @ResponseBody
    public List<Map<String, ?>> update(String sql) {
        return null;
    }

    @DeleteMapping
    @ResponseBody
    public List<Map<String, ?>> delete(String sql) {
        return null;
    }

}
