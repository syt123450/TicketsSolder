package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.utils.GeneratorUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ss on 2017/11/22.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketsDaoTest {

    @Autowired
    private TicketsDao ticketsDao;

    @Test
    @Ignore
    public void testClearTicketsHistory() {
        ticketsDao.clearTicketsHistory();
    }

    @Test
    @Ignore
    public void testUpdateTickets() {

        ticketsDao.insertNewTickets(GeneratorUtils.generateTicketsLine(1000));
    }

    @Test
    @Ignore
    public void testPurchaseTickets() {

        String trainName = "SB0600";
        List<String> segments = GeneratorUtils.generateSegments('A', 'K');

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 19);

        Date date = new Date(calendar.getTimeInMillis());

        System.out.println(date);

        ticketsDao.purchaseTickets(trainName, segments, date, 4);
    }

    @Test
    @Ignore
    public void testReturnTickets() {

        String trainName = "SB0600";
        List<String> segments = GeneratorUtils.generateSegments('A', 'K');

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 19);

        Date date = new Date(calendar.getTimeInMillis());

        System.out.println(date);

        ticketsDao.returnTickets(trainName, segments, date, 4);
    }
}