package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.trip.BasicTripSearchRequest;

import java.sql.Date;
import java.sql.Time;
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

    public static Calendar getEndCalendar(Date startDay, Time endTime, Calendar startCalendar, Calendar endCalendar) {

        endCalendar.setTime(startDay);
        endCalendar.setTime(endTime);
        endCalendar.add(Calendar.MINUTE, -3);

        if (startCalendar.getTimeInMillis() > endCalendar.getTimeInMillis()) {
            endCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return endCalendar;
    }

    public static Calendar getStartCalendarForSearch(BasicTripSearchRequest basicTripSearchRequest) {

        Calendar startCalendar = Calendar.getInstance();

        startCalendar.set(Calendar.YEAR, basicTripSearchRequest.getYear());
        startCalendar.set(Calendar.MONTH, basicTripSearchRequest.getMonth());
        startCalendar.set(Calendar.DAY_OF_MONTH, basicTripSearchRequest.getDay());
        startCalendar.set(Calendar.HOUR_OF_DAY, basicTripSearchRequest.getHour());
        startCalendar.set(Calendar.MINUTE, basicTripSearchRequest.getMinute());
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);

        return startCalendar;
    }
}
