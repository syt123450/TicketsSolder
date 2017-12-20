package com.ticketSolder.model.utils;

import com.ticketSolder.model.dao.mysql.TransactionDao;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/5.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimeUtilsTest {

    @Autowired
    private TransactionDao transactionDao;

    @Test
    @Ignore
    public void testGetCalendarFromSQLTimer() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        Time time = new Time(calendar.getTimeInMillis());
        System.out.println(time);
        calendar.setTime(time);
        System.out.println(calendar.getTime());
    }

    @Test
    @Ignore
    public void testValidateTransactionCancelTime() {
        System.out.println(TimeUtils.validateTransactionCancelTime(
                transactionDao.getTransactionDepartureTime(41)));
    }
}