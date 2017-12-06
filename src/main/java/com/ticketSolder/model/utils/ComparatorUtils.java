package com.ticketSolder.model.utils;

import com.ticketSolder.model.domain.mysql.SearchResultUnit;

import java.sql.Date;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Created by ss on 2017/12/5.
 */
public class ComparatorUtils {

    private static class SearchResultUnitComparator implements Comparator<SearchResultUnit> {

        private Date startDate;

        public SearchResultUnitComparator(Date startDate) {
            this.startDate = startDate;
        }

        @Override
        public int compare(SearchResultUnit searchResultUnit1,
                           SearchResultUnit searchResultUnit2) {

            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();

            calendar1.setTime(startDate);
            calendar1.setTime(searchResultUnit1.getEndTime());
            calendar2.setTime(startDate);
            calendar2.setTime(searchResultUnit2.getEndTime());

            long difference = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();

            int a;

            if (difference > 0) {
                a = 1;
            } else {
                a = -1;
            }

            return a;
        }
    }

    public static Comparator<SearchResultUnit> getSearchResultUnitComparator(Date startDate) {

        return new SearchResultUnitComparator(startDate);
    }
}
