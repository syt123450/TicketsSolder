package com.ticketSolder.model.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/11/23.
 */
public class SearchHelperTest {

    @Test
    @Ignore
    public void test() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2000);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        cal1.set(Calendar.HOUR_OF_DAY, 1);
        Date date = new Date(cal1.getTimeInMillis());

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-DD");
        System.out.println(simpleDateFormat.format(date));
    }
}