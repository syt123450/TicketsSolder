package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ss on 2017/11/16.
 */
public interface UserDao {

    int createUser(@Param("userName") String userName, @Param("password") String password);

    UserInfo searchUser(@Param("userName") String userName, @Param("password") String password);

    UserInfo searchUserByName(@Param("userName") String userName);
}