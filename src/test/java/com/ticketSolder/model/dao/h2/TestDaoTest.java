package com.ticketSolder.model.dao.h2;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/11/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestDaoTest {

    @Autowired
    private TestDao testDao;

    @Test
    @Ignore
    public void test() {
        testDao.insert();
        System.out.println(testDao.select());
    }

    @Test
    @Ignore
    public void testInit() {
        System.out.println(testDao.testInit());
    }
}