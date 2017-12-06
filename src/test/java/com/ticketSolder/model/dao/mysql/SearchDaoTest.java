package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.SearchResultUnit;
import com.ticketSolder.model.utils.GeneratorUtils;
import org.apache.ibatis.annotations.Param;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/11/24.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchDaoTest {

    @Autowired
    private SearchDao searchDao;

    @Test
    @Ignore
    public void testSearch() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date startDate = new Date(calendar.getTimeInMillis());
        Time startTime = new Time(calendar.getTimeInMillis());
        char startStation = 'A';
        char endStation = 'C';
        List<String> segments = GeneratorUtils.generateSegments(startStation, endStation);
        boolean direction = true;

        searchDao.search(startDate, startTime, startStation, endStation, segments, direction, false).forEach(System.out::println);
    }
}