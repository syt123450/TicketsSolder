package com.ticketSolder.model.utils;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by ss on 2017/11/27.
 */
public class TimeUtils {

    public static Date getSQLDate(int year, int month, int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return new Date(calendar.getTimeInMillis());
    }
}
