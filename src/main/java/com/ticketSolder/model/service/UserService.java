package com.ticketSolder.model.service;

import com.ticketSolder.model.bean.AuthenticationResult;
import com.ticketSolder.model.bean.CreationResult;

/**
 * Created by ss on 2017/11/16.
 */
public interface UserService {

    CreationResult createUser(String userName, String password);

    AuthenticationResult authenticate(String userName, String password);
}
