package com.ddm.controller;

import com.ddm.model.UserCondition;
import com.ddm.model.UserDomain;
import com.ddm.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yunpeng.song on 6/13/2018.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user) {
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5")
                    int pageSize) {
        //Test if it works, if put pageHelper in controller.
        PageHelper.startPage(pageNum, pageSize);

        return userService.findAllUser(pageNum, pageSize);
    }

    @ResponseBody
    @GetMapping("/findBy")
    public Object findUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5")
                    int pageSize,
            @RequestParam(name = "userName", required = false)
                    String userName,
            @RequestParam(name = "password", required = false)
                    String password,
            @RequestParam(name = "phone", required = false)
                    String phone) {
        //Test if it works, if put pageHelper in controller.
        PageHelper.startPage(pageNum, pageSize);

        if ("".equals(userName)) {
            userName = null;
        }
        if ("".equals(password)) {
            password = null;
        }
        if ("".equals(phone)) {
            phone = null;
        }
        UserCondition uc = new UserCondition(userName, password, phone);

        return userService.findUserBy(pageNum, pageSize, uc);
    }
}
