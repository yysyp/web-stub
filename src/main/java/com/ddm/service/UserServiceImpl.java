package com.ddm.service;

import com.ddm.dao.UserMapper;
import com.ddm.model.UserCondition;
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
    private UserMapper userMapper;

    @Override
    public int addUser(UserDomain user) {

        return userMapper.insert(user);
    }


    @Override
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {

        //PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userMapper.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }

    @Override
    public PageInfo<UserDomain> findUserBy(int pageNum, int pageSize, UserCondition uc) {
        List<UserDomain> userDomains = userMapper.selectUsersByCondition(uc);
        PageInfo result = new PageInfo(userDomains);
        return result;
    }
}
