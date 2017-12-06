package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.trip.TripInfo;
import com.ticketSolder.model.domain.mysql.SearchResultUnit;

import java.sql.Date;
import java.util.List;

/**
 * Created by ss on 2017/12/6.
 */
public class TailorUtils {

    public static List<TripInfo> curtailBothTrips(List<TripInfo> tripInfoList) {

        tripInfoList.sort(ComparatorUtils.getTripInfoComparator());

        if (tripInfoList.size() < 5) {
            return tripInfoList;
        } else {
            return tripInfoList.subList(0, 5);
        }
    }

    public static List<List<SearchResultUnit>> curtailFastSegments(List<List<SearchResultUnit>> trips, Date startDate) {

        trips.sort(ComparatorUtils.getFastTripSegmentsComparator(startDate));

        if (trips.size() < 5) {
            return trips;
        } else {
            return trips.subList(0, 5);
        }
    }

    public static List<SearchResultUnit> curtailFastOneSegmentTrips(List<SearchResultUnit> searchResultUnits, Date startDate) {

        return curtailOneSegmentTrips(searchResultUnits, startDate);
    }

    public static List<SearchResultUnit> curtailNormalTrips(List<SearchResultUnit> searchResultUnits, Date startDate) {

        return curtailOneSegmentTrips(searchResultUnits, startDate);
    }

    private static List<SearchResultUnit> curtailOneSegmentTrips(List<SearchResultUnit> searchResultUnits,
                                                                 Date startDate) {
        searchResultUnits.sort(ComparatorUtils.getSearchResultUnitComparator(startDate));

        if (searchResultUnits.size() < 5) {
            return searchResultUnits;
        } else {
            return searchResultUnits.subList(0, 5);
        }
    }
}
