package com.ticketSolder.model.dao;

import com.ticketSolder.model.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ss on 2017/11/16.
 */
public interface UserDao {

    //    int createUser(String userName, String password);
    UserInfo searchUser(@Param("userName") String userName, @Param("password") String password);

    UserInfo searchUserByName(@Param("userName") String userName);
}