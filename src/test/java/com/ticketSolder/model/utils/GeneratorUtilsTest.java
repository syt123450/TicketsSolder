package com.ticketSolder.model.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by ss on 2017/11/24.
 */
public class GeneratorUtilsTest {

    @Test
    @Ignore
    public void testGenerateSegments() {
        System.out.println(GeneratorUtils.generateSegments('A', 'C'));
    }

    @Test
    @Ignore
    public void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        System.out.println(calendar.getTime());

        Date date = new Date(calendar.getTimeInMillis());

        System.out.println(date);

        Time time = new Time(calendar.getTimeInMillis());

        System.out.println(time);

        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(date);
        newCalendar.setTime(time);

        System.out.println(newCalendar.getTime());
    }
}