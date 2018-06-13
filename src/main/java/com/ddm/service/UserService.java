package com.ddm.service;

import com.ddm.model.UserDomain;
import com.github.pagehelper.PageInfo;

/**
 * Created by yunpeng.song on 6/13/2018.
 */
public interface UserService {

    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
}
