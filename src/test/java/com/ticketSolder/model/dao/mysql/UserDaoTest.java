package com.ticketSolder.model.dao.mysql;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/11/29.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Ignore
    public void testCreateUser() {
        userDao.createUser("Hello world", "syt19930224", "syt123450@gmail.com");
    }

    @Test
    @Ignore
    public void testSearchUser() {
        System.out.println(userDao.searchUser("syt123450", "hihi"));
        System.out.println(userDao.searchUser("syt123450", "123456"));
    }

    @Test
    @Ignore
    public void testSearchUserByName() {
        System.out.println(userDao.searchUserByName("ttt"));
        System.out.println(userDao.searchUserByName("syt123450"));
    }

    @Test
    @Ignore
    public void testGetEmailByName() {
        System.out.println(userDao.getEmailByName("ttt"));
        System.out.println(userDao.getEmailByName("syt123450"));
    }

    @Test
    @Ignore
    public void testDeleteAllUsers() {
        userDao.deleteAllUsers();
    }
}