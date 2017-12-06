package com.ticketSolder.model.utils;

import com.ticketSolder.model.domain.mysql.SearchResultUnit;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/5.
 */
public class ComparatorUtilsTest {

    @Test
    @Ignore
    public void testGetSearchResultUnitComparator() {
        List<SearchResultUnit> list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Calendar calendar1 = Calendar.getInstance();

        calendar1.set(Calendar.YEAR, 2017);
        calendar1.set(Calendar.MONTH, 10);
        calendar1.set(Calendar.DAY_OF_MONTH, 30);
        calendar1.set(Calendar.HOUR_OF_DAY, 8);
        calendar1.set(Calendar.MINUTE, 15);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);

        SearchResultUnit searchResultUnit = new SearchResultUnit(
                "SB0600",
                true,
                new Time(calendar.getTimeInMillis()),
                new Time(calendar.getTimeInMillis()),
                100
        );

        SearchResultUnit searchResultUnit1 = new SearchResultUnit(
                "SB0615",
                true,
                new Time(calendar1.getTimeInMillis()),
                new Time(calendar1.getTimeInMillis()),
                100
        );

        list.add(searchResultUnit);
        list.add(searchResultUnit1);

        list.sort(ComparatorUtils.getSearchResultUnitComparator(new Date(calendar.getTimeInMillis())));

        list.forEach(System.out::println);
    }
}