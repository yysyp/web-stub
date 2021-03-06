package com.ddm.dao;

import com.ddm.model.UserCondition;
import com.ddm.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by yunpeng.song on 6/13/2018.
 */
//@Mapper
public interface UserMapper {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();

    List<UserDomain> selectUsersByCondition(UserCondition userCondition);
}
