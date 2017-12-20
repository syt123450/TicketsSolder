package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.trip.BasicTripSearchRequest;
import com.ticketSolder.model.domain.mysql.DepartureTime;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ss on 2017/11/27.
 */
public class TimeUtils {

    private static Logger logger = Logger.getLogger(TimeUtils.class);

    public static Date getSQLDate(int year, int month, int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return new Date(calendar.getTimeInMillis());
    }

    public static Calendar getEndCalendar(Date startDay, Time endTime, Calendar startCalendar) {

        Calendar endCalendar = getCalendarFromSQLTimer(startDay, endTime);
        endCalendar.add(Calendar.MINUTE, -3);

        if (startCalendar.getTimeInMillis() > endCalendar.getTimeInMillis()) {
            endCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return endCalendar;
    }

    public static Calendar getStartCalendarForSearch(BasicTripSearchRequest basicTripSearchRequest) {

        Calendar startCalendar = Calendar.getInstance();

        startCalendar.set(Calendar.YEAR, basicTripSearchRequest.getYear());
        startCalendar.set(Calendar.MONTH, basicTripSearchRequest.getMonth() - 1);
        startCalendar.set(Calendar.DAY_OF_MONTH, basicTripSearchRequest.getDay());
        startCalendar.set(Calendar.HOUR_OF_DAY, basicTripSearchRequest.getHour());
        startCalendar.set(Calendar.MINUTE, basicTripSearchRequest.getMinute());
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);

        return startCalendar;
    }

    public static Calendar getCalendarFromSQLTimer(Date date, Time time) {

        logger.debug("format date: " + date + ", format date: " + time);

        Calendar calendar = Calendar.getInstance();

        Calendar helperCalendar = Calendar.getInstance();
        helperCalendar.setTime(date);

        calendar.set(Calendar.YEAR, helperCalendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, helperCalendar.get(Calendar.MONTH));

        calendar.set(Calendar.DAY_OF_MONTH, helperCalendar.get(Calendar.DAY_OF_MONTH));

        helperCalendar.setTime(time);

        calendar.set(Calendar.HOUR_OF_DAY, helperCalendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, helperCalendar.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, helperCalendar.get(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, helperCalendar.get(Calendar.MILLISECOND));

        logger.debug("Format to calendar " + calendar.getTime());

        return calendar;
    }

    public static Calendar getCalendarFromString(String dateString, String timeString) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(
                    simpleDateFormat.parse(dateString + " " + timeString)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return calendar;
    }

    public static boolean validateTransactionCancelTime(DepartureTime departureTime) {

        long timeNow = Calendar.getInstance().getTimeInMillis();

        Calendar departureCalendar = Calendar.getInstance();
        Calendar helperCalendar = Calendar.getInstance();

        departureCalendar.setTime(departureTime.getStartTime());
        helperCalendar.setTime(departureTime.getStartDate());

        departureCalendar.set(Calendar.YEAR, helperCalendar.get(Calendar.YEAR));
        departureCalendar.set(Calendar.MONTH, helperCalendar.get(Calendar.MONTH));
        departureCalendar.set(Calendar.DAY_OF_MONTH, helperCalendar.get(Calendar.DAY_OF_MONTH));

        long departureTimeInMillis = departureCalendar.getTimeInMillis();

        long differenceHours = (departureTimeInMillis - timeNow) / 1000 / 60 / 60;

        logger.info("The departure time are " + differenceHours + " hours later.");

        return differenceHours > 1;
    }
}
