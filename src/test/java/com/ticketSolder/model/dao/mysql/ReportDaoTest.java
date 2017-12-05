package com.ticketSolder.model.dao.mysql;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void testGetTrainTotalReports() {
        reportDao.getTrainTotalReports().forEach(System.out::println);
    }
}