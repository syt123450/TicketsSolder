package com.ticketSolder.model.service;

import com.ticketSolder.model.bean.user.AuthenticationResult;
import com.ticketSolder.model.bean.user.UserCreationResult;

/**
 * Created by ss on 2017/11/16.
 */
public interface UserService {

    UserCreationResult createUser(String userName, String password);

    AuthenticationResult authenticate(String userName, String password);
}
