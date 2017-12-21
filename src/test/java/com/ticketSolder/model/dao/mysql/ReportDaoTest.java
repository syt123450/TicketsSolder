package com.ticketSolder.model.dao.mysql;

import afu.org.checkerframework.checker.igj.qual.I;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/4.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportDaoTest {

    @Autowired
    private ReportDao reportDao;

    @Test
    @Ignore
    public void testGetTrainReports() {
        reportDao.getTrainReports("SB0600").forEach(System.out::println);
    }

    @Test
    @Ignore
    public void testGetTrainTotalReports() {
        reportDao.getTrainTotalReports().forEach(System.out::println);
    }

    @Test
    @Ignore
    public void testGetPerTrainReservationRate() {

        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());

        System.out.println(reportDao.getPerTrainReservationRate("SB0600", date));
    }

    @Test
//    @Ignore
    public void testGetSystemReservationRate() {

        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());

        System.out.println(reportDao.getSystemReservationRate(date));
    }

    @Test
    public void testGetSearchState() {

        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());

        System.out.println(reportDao.getSearchState(date));
    }
}