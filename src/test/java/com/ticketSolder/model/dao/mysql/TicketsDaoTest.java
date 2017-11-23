package com.ticketSolder.model.dao.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/11/22.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketsDaoTest {

    @Autowired
    private TicketsDao ticketsDao;

    @Test
    public void testClearTicketsHistory() {
        ticketsDao.clearTicketsHistory();
    }
}