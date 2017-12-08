package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.service.rest.UserHandler;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/6.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserHandlerImplTest {

    @Autowired
    private UserHandler userHandler;

    @Test
    @Ignore
    public void testCreateUser() {
        userHandler.createUser("sy", "syt19930224", "syt123450@gmail.com");
    }

    @Test
    @Ignore
    public void testAuthenticate() {
        System.out.println(userHandler.authenticate("syt123450", "1212"));
        System.out.println(userHandler.authenticate("syt123450", "123456"));
    }

    @Test
    @Ignore
    public void testGoogleAuthenticate() {
        System.out.println(userHandler.googleAuthenticate("lll", "123", "tt"));
    }
}