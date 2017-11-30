package com.ticketSolder.model.service.rest;

import com.ticketSolder.model.bean.user.AuthenticationResult;
import com.ticketSolder.model.bean.user.UserCreationResult;

/**
 * Created by ss on 2017/11/16.
 */
public interface UserHandler {

    UserCreationResult createUser(String userName, String password, String email);

    AuthenticationResult authenticate(String userName, String password);
}
