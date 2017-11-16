package com.ticketSolder.model.service;

import com.ticketSolder.model.bean.AuthenticateResult;
import com.ticketSolder.model.bean.CreationResult;

/**
 * Created by ss on 2017/11/16.
 */
public interface UserService {

    CreationResult createUser(String userName, String password);
    AuthenticateResult authenticate(String userName, String password);
}
