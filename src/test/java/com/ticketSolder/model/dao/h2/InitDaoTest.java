package com.ticketSolder.model.dao.h2;

import com.ticketSolder.model.dao.mysql.InitHelperDao;
import org.junit.Ignore;
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
public class InitDaoTest {

    @Autowired
    private InitDao initDao;
    @Autowired
    private InitHelperDao initHelperDao;

    @Test
    @Ignore
    public void testInitTickets() {
        initDao.initTickets(initHelperDao.extractTicketsData());
    }
}