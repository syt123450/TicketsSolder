package com.ticketSolder.model.service.rest.mock;

import com.ticketSolder.model.bean.user.AuthenticationResult;
import com.ticketSolder.model.bean.user.UserCreationResult;
import com.ticketSolder.model.service.rest.UserHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/29.
 */

@Service
@Profile("mock")
public class MockUserHandler implements UserHandler {

    @Override
    public UserCreationResult createUser(String userName, String password, String email) {

        UserCreationResult userCreationResult = new UserCreationResult();

        userCreationResult.setResult(true);
        userCreationResult.setReason("Fail to create user.");

        return userCreationResult;
    }

    @Override
    public AuthenticationResult authenticate(String userName, String password) {

        AuthenticationResult authenticationResult = new AuthenticationResult();

        authenticationResult.setResult(true);

        return authenticationResult;
    }
}