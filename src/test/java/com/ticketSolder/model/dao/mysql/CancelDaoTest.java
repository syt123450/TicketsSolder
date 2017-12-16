package com.ticketSolder.model.dao.mysql;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/11/30.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CancelDaoTest {

    @Autowired
    private CancelDao cancelDao;

    @Test
    @Ignore
    public void testGetStartTime() {
        System.out.println(cancelDao.getStartTime("NB0600", false, true));
    }

    @Test
    @Ignore
    public void testCancelTrain() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        Date date = new Date(calendar.getTimeInMillis());

        System.out.println(cancelDao.cancelTrain("SB0600", date));
    }

    @Test
    @Ignore
    public void testFindTransactions() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        Date date = new Date(calendar.getTimeInMillis());

        cancelDao.findTransactions("SB0615", date).forEach(System.out::println);
    }

    @Test
    @Ignore
    public void testCancelTickets() {
        List<Long> transactionIds = new ArrayList<>();
        transactionIds.add(10L);
        transactionIds.add(11L);
        System.out.println(transactionIds);
        System.out.println(cancelDao.cancelTickets(transactionIds));
    }
}