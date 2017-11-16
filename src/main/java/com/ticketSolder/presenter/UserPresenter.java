package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ss on 2017/11/16.
 */

@EnableAutoConfiguration
@RestController
@RequestMapping("/api/user")
public class UserPresenter {

    private Gson gson = new GsonBuilder().create();
    private Logger logger = Logger.getLogger(UserPresenter.class);

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    private String authenticate(@RequestBody String body) {

        logger.info("Authenticate user.");

        return "";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private String register(@RequestBody String body) {

        logger.info("Register new user.");

        return "";
    }
}
