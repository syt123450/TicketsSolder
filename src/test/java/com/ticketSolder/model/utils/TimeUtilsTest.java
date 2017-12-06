package com.ticketSolder.model.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.Time;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/5.
 */
public class TimeUtilsTest {

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
}