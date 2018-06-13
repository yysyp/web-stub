package com.ddm.service;

import com.ddm.dao.UserDao;
import com.ddm.model.UserDomain;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yunpeng.song on 6/13/2018.
 */
@Service(value= "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(UserDomain user) {

        return userDao.insert(user);
    }


    @Override
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }
}
