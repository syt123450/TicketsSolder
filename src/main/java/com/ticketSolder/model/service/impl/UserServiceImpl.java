package com.ticketSolder.model.service.impl;

import com.ticketSolder.model.bean.AuthenticationResult;
import com.ticketSolder.model.dao.UserDao;
import com.ticketSolder.model.bean.CreationResult;
import com.ticketSolder.model.domain.UserInfo;
import com.ticketSolder.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/16.
 */

@Service
public class UserServiceImpl implements UserService {

    private static final String FAIL_MESSAGE = "User already existed.";

    @Autowired
    private UserDao userDao;

    @Override
    public CreationResult createUser(String userName, String password) {

        CreationResult creationResult = new CreationResult();

        UserInfo userInfo = userDao.searchUserByName(userName);
        if (userInfo != null) {
            creationResult.setResult(false);
            creationResult.setReason(FAIL_MESSAGE);
            return creationResult;
        }

        int result = userDao.createUser(userName, password);
        creationResult.setResult(result == 1);

        return creationResult;
    }

    @Override
    public AuthenticationResult authenticate(String userName, String password) {

        AuthenticationResult authenticationResult = new AuthenticationResult();

        UserInfo userInfo = userDao.searchUser(userName, password);
        authenticationResult.setResult(userInfo != null);

        return authenticationResult;
    }
}