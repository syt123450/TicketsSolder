package com.ticketSolder.model.dao.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/7.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InitHelperDaoTest {

    @Autowired
    private InitHelperDao initHelperDao;

    @Test
    public void testExtractTicketsData() {
        initHelperDao.extractTicketsData().forEach(System.out::println);
    }
}