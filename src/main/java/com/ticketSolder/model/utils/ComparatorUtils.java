package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.trip.TripInfo;
import com.ticketSolder.model.domain.mysql.SearchResultUnit;

import java.sql.Date;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

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

            Calendar startCalendar1 = TimeUtils.getCalendarFromSQLTimer(startDate, searchResultUnit1.getStartTime());
            Calendar startCalendar2 = TimeUtils.getCalendarFromSQLTimer(startDate, searchResultUnit2.getStartTime());


            Calendar endCalendar1 = TimeUtils.getEndCalendar(startDate,
                    searchResultUnit1.getEndTime(),
                    startCalendar1);
            Calendar endCalendar2 = TimeUtils.getEndCalendar(startDate,
                    searchResultUnit2.getEndTime(),
                    startCalendar2);

            long difference = endCalendar1.getTimeInMillis() - endCalendar2.getTimeInMillis();

            int a;

            if (difference > 0) {
                a = 1;
            } else {
                a = -1;
            }

            return a;
        }
    }

    private static class FastTripSegmentsComparator implements Comparator<List<SearchResultUnit>> {

        private Date startDate;

        public FastTripSegmentsComparator(Date startDate) {
            this.startDate = startDate;
        }

        @Override
        public int compare(List<SearchResultUnit> searchResultUnitList1,
                           List<SearchResultUnit> searchResultUnitList2) {

            Calendar startCalendar1 = TimeUtils.getCalendarFromSQLTimer(startDate,
                    searchResultUnitList1.get(searchResultUnitList1.size() - 1).getStartTime());
            Calendar startCalendar2 = TimeUtils.getCalendarFromSQLTimer(startDate,
                    searchResultUnitList2.get(searchResultUnitList2.size() - 1).getStartTime());

            Calendar endCalendar1 = TimeUtils.getEndCalendar(startDate,
                    searchResultUnitList1.get(searchResultUnitList1.size() - 1).getEndTime(),
                    startCalendar1);
            Calendar endCalendar2 = TimeUtils.getEndCalendar(startDate,
                    searchResultUnitList2.get(searchResultUnitList2.size() - 1).getEndTime(),
                    startCalendar2);

            long difference = endCalendar1.getTimeInMillis() - endCalendar2.getTimeInMillis();

            int a = 0;

            if (difference > 0) {
                a = 1;
            } else if (difference < 0) {
                a = -1;
            } else {
                Calendar start1 = TimeUtils.getCalendarFromSQLTimer(startDate,
                        searchResultUnitList1.get(0).getStartTime());
                Calendar start2 = TimeUtils.getCalendarFromSQLTimer(startDate,
                        searchResultUnitList1.get(0).getStartTime());

                long tripTime1 = endCalendar1.getTimeInMillis() - start1.getTimeInMillis();
                long tripTime2 = endCalendar2.getTimeInMillis() - start2.getTimeInMillis();

                long difference2 = tripTime1 - tripTime2;

                if (difference2 > 0) {
                    a = 1;
                } else {
                    a = -1;
                }

            }

            return a;
        }
    }

    private static class TripInfoComparator implements Comparator<TripInfo> {

        @Override
        public int compare(TripInfo tripInfo1, TripInfo tripInfo2) {

            Calendar endCalendar1 = TimeUtils.getCalendarFromString(
                    tripInfo1.getSegments().get(tripInfo1.getSegments().size() - 1).getEndDay(),
                    tripInfo1.getSegments().get(tripInfo1.getSegments().size() - 1).getEndTime()
            );

            Calendar endCalendar2 = TimeUtils.getCalendarFromString(
                    tripInfo2.getSegments().get(tripInfo2.getSegments().size() - 1).getEndDay(),
                    tripInfo2.getSegments().get(tripInfo2.getSegments().size() - 1).getEndTime()
            );

            long difference = endCalendar1.getTimeInMillis() - endCalendar2.getTimeInMillis();

            int a;

            if (difference > 0) {
                a = 1;
            } else if (difference < 0){
                a = -1;
            } else {
                Calendar start1 = TimeUtils.getCalendarFromString(
                        tripInfo1.getSegments().get(0).getStartDay(),
                        tripInfo1.getSegments().get(0).getStartTime()
                );

                Calendar start2 = TimeUtils.getCalendarFromString(
                        tripInfo2.getSegments().get(0).getStartDay(),
                        tripInfo2.getSegments().get(0).getStartTime()
                );

                long tripTime1 = endCalendar1.getTimeInMillis() - start1.getTimeInMillis();
                long tripTime2 = endCalendar2.getTimeInMillis() - start2.getTimeInMillis();

                long difference2 = tripTime1 - tripTime2;

                if (difference2 > 0) {
                    a = 1;
                } else {
                    a = -1;
                }
            }

            return a;
        }
    }

    public static Comparator<SearchResultUnit> getSearchResultUnitComparator(Date startDate) {

        return new SearchResultUnitComparator(startDate);
    }

    public static Comparator<List<SearchResultUnit>> getFastTripSegmentsComparator(Date startDate) {

        return new FastTripSegmentsComparator(startDate);
    }

    public static Comparator<TripInfo> getTripInfoComparator() {

        return new TripInfoComparator();
    }
}
