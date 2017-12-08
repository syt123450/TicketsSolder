package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.NewUser;
import com.ticketSolder.model.domain.mysql.TestBean;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by ss on 2017/11/22.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TesterDaoTest {

    @Autowired
    private TesterDao testerDao;

    @Test
    @Ignore
    public void test() {

        TestBean bean = new TestBean();
//        bean.setName("111");

        System.out.println(testerDao.test(bean));
    }

    @Test
    @Ignore
    public void testColumnName() {

        System.out.println(testerDao.hello("userName"));
    }

    @Test
    @Ignore
    public void testTestDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Time date = new Time(calendar.getTimeInMillis());

        System.out.println(date);

        System.out.println(testerDao.testTime(date));
    }

    @Test
    @Ignore
    public void testTestIf() {
        System.out.println(testerDao.testIf(false));
    }

    @Test
    @Ignore
    public void testTestOneToMany() {
        NewUser newUser = testerDao.testOneToMany();
        System.out.println(newUser);
        System.out.println(newUser.getOrders());
    }
}