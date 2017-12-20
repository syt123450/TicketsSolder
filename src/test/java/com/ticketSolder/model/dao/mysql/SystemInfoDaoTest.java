package com.ticketSolder.model.dao.mysql;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/20.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemInfoDaoTest {

    @Autowired
    private SystemInfoDao systemInfoDao;

    @Test
    @Ignore
    public void testInitSystemInfo() {
        systemInfoDao.initSystemInfo(1000);
    }
}