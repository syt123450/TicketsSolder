package com.ticketSolder.model.utils;

import com.ticketSolder.model.domain.mysql.SearchResultUnit;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ss on 2017/12/6.
 */
public class CombinationUtils {

    public static List<List<SearchResultUnit>> combineSearchResultUnits(List<List<SearchResultUnit>> searchResultLists,
                                                                        Date startDate) {

        List<List<SearchResultUnit>> combineResultList = new ArrayList<>();

        for (int i = 0; i < searchResultLists.size(); i++) {
            combineResultList = combine(combineResultList, searchResultLists.get(i), startDate);
            if (combineResultList == null) {
                return new ArrayList<>();
            }
        }

        return combineResultList;
    }

    private static List<List<SearchResultUnit>> combine(List<List<SearchResultUnit>> previousList,
                                                       List<SearchResultUnit> nowUnits,
                                                       Date startDate) {

        List<List<SearchResultUnit>> intermediateList = new ArrayList<>();

        if (previousList.size() == 0) {
            for (SearchResultUnit searchResultUnit: nowUnits) {
                List<SearchResultUnit> newList = new ArrayList<>();
                newList.add(searchResultUnit);
                intermediateList.add(newList);
            }

            return intermediateList;
        }

        for (int i = 0; i < previousList.size(); i++) {
            for (int j = 0; j < nowUnits.size(); j++) {
                if (validateCombination(previousList.get(i).get(previousList.get(i).size() - 1),
                        nowUnits.get(j),
                        startDate)) {
                    List<SearchResultUnit> tempTrip = new ArrayList<>();
                    for (SearchResultUnit searchResultUnit: previousList.get(i)) {
                        tempTrip.add(searchResultUnit);
                    }
                    tempTrip.add(nowUnits.get(j));
                    intermediateList.add(tempTrip);
                }
            }
        }

        return intermediateList;
    }

    private static boolean validateCombination(SearchResultUnit searchResultUnit1,
                                              SearchResultUnit searchResultUnit2,
                                              Date startDate) {

        Calendar startCalendar1 = TimeUtils.getCalendarFromSQLTimer(startDate, searchResultUnit1.getStartTime());
        Calendar startCalendar2 = TimeUtils.getCalendarFromSQLTimer(startDate, searchResultUnit2.getStartTime());

        Calendar endCalendar1 = TimeUtils.getEndCalendar(startDate,
                searchResultUnit1.getEndTime(),
                startCalendar1);

        long differentHour = (startCalendar2.getTimeInMillis() - endCalendar1.getTimeInMillis()) / 1000 / 60 / 60;

        return differentHour > 0 && differentHour < 2;
    }
}