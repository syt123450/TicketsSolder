package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketSolder.model.bean.user.CreateUserRequest;
import com.ticketSolder.model.bean.user.GoogleAuthenticateRequest;
import com.ticketSolder.model.bean.user.UserInfoRequest;
import com.ticketSolder.model.service.rest.UserHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ss on 2017/11/16.
 */

@EnableAutoConfiguration
@RestController
@RequestMapping("/api/user")
class UserPresenter {

    @Autowired
    private UserHandler userHandler;
    private Gson gson = new GsonBuilder().create();
    private Logger logger = Logger.getLogger(UserPresenter.class);

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    private String authenticate(@RequestBody String body) {

        logger.info("Authenticate user.");

        UserInfoRequest userInfoRequest = gson.fromJson(body, UserInfoRequest.class);

        return gson.toJson(userHandler.authenticate(userInfoRequest.getUserName(), userInfoRequest.getPassword()));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private String register(@RequestBody String body) {

        logger.info("Register new user.");

        CreateUserRequest createUserRequest = gson.fromJson(body, CreateUserRequest.class);

        return gson.toJson(userHandler.createUser(createUserRequest.getUserName(),
                        createUserRequest.getPassword(),
                        createUserRequest.getEmail()));
    }

    @RequestMapping(value = "/google", method = RequestMethod.POST)
    private String loginByGoogle(@RequestBody String body) {

        logger.info("Handle google login.");

        GoogleAuthenticateRequest googleAuthenticateRequest = gson.fromJson(body, GoogleAuthenticateRequest.class);

        return gson.toJson(userHandler.googleAuthenticate(
                googleAuthenticateRequest.getUserName(),
                googleAuthenticateRequest.getPassword(),
                googleAuthenticateRequest.getEmail()
        ));
    }
}