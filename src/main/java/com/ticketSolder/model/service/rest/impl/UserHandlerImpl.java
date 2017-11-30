package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.user.AuthenticationResult;
import com.ticketSolder.model.dao.mysql.UserDao;
import com.ticketSolder.model.bean.user.UserCreationResult;
import com.ticketSolder.model.domain.mysql.UserInfo;
import com.ticketSolder.model.service.rest.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/16.
 */

@Service
@Profile("dev")
public class UserHandlerImpl implements UserHandler {

    private static final String FAIL_MESSAGE = "User already existed.";

    @Autowired
    private UserDao userDao;

    @Override
    public UserCreationResult createUser(String userName, String password, String email) {

        UserCreationResult userCreationResult = new UserCreationResult();

        UserInfo userInfo = userDao.searchUserByName(userName);
        if (userInfo != null) {
            userCreationResult.setResult(false);
            userCreationResult.setReason(FAIL_MESSAGE);
            return userCreationResult;
        }

        int result = userDao.createUser(userName, password, email);
        userCreationResult.setResult(result == 1);

        return userCreationResult;
    }

    @Override
    public AuthenticationResult authenticate(String userName, String password) {

        AuthenticationResult authenticationResult = new AuthenticationResult();

        UserInfo userInfo = userDao.searchUser(userName, password);
        authenticationResult.setResult(userInfo != null);

        return authenticationResult;
    }
}