package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.trip.BasicTripSearchRequest;
import com.ticketSolder.model.bean.trip.SearchOutputSegmentInfo;
import com.ticketSolder.model.bean.trip.SlicedSegment;
import com.ticketSolder.model.bean.trip.TripInfo;
import com.ticketSolder.model.domain.mysql.SearchResultUnit;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ss on 2017/12/6.
 */
public class FastResultCombiner {

    private static Logger logger = Logger.getLogger(FastResultCombiner.class);

    public static List<TripInfo> combine(BasicTripSearchRequest basicTripSearchRequest,
                                         List<SlicedSegment> slicedSegments,
                                         List<List<SearchResultUnit>> resultLists) {

        if (slicedSegments.size() == 1) {
            return combineOneSegmentFastTrip(basicTripSearchRequest, slicedSegments, resultLists);
        } else {
            return combineSeveralSegmentFastTrip(basicTripSearchRequest, slicedSegments, resultLists);
        }
    }

    private static List<TripInfo> combineOneSegmentFastTrip(BasicTripSearchRequest basicTripSearchRequest,
                                                    List<SlicedSegment> slicedSegments,
                                                    List<List<SearchResultUnit>> resultLists) {

        logger.info("Combine fast one segment.");

        Calendar startCalendar = TimeUtils.getStartCalendarForSearch(basicTripSearchRequest);
        Date startDate = new Date(startCalendar.getTimeInMillis());

        List<SearchResultUnit> curtailedUnits = TailorUtils.curtailFastOneSegmentTrips(resultLists.get(0), startDate);

        //format the result

        List<TripInfo> tripInfoList = new ArrayList<>();

        for (SearchResultUnit searchResultUnit : curtailedUnits) {

            TripInfo tripInfo = new TripInfo();
            List<SearchOutputSegmentInfo> searchOutputSegmentInfoList = new ArrayList<>();

            SearchOutputSegmentInfo searchOutputSegmentInfo = GeneratorUtils.generateSearchOutputSegmentInfo(
                    basicTripSearchRequest,
                    searchResultUnit,
                    startCalendar,
                    startDate
            );

            searchOutputSegmentInfoList.add(searchOutputSegmentInfo);
            tripInfo.setSegments(searchOutputSegmentInfoList);

            tripInfoList.add(tripInfo);
        }

        return tripInfoList;
    }

    private static List<TripInfo> combineSeveralSegmentFastTrip(BasicTripSearchRequest basicTripSearchRequest,
                                                    List<SlicedSegment> slicedSegments,
                                                    List<List<SearchResultUnit>> resultLists) {

        logger.info("Combine fast several segments.");

        Calendar startCalendar = TimeUtils.getStartCalendarForSearch(basicTripSearchRequest);
        Date startDate = new Date(startCalendar.getTimeInMillis());

        List<List<SearchResultUnit>> trips = CombinationUtils.combineSearchResultUnits(resultLists, startDate);

        List<List<SearchResultUnit>> curtailedTrips = TailorUtils.curtailFastSegments(trips, startDate);

        List<TripInfo> tripInfoList = new ArrayList<>();

        for (List<SearchResultUnit> trip: curtailedTrips) {
            TripInfo tripInfo = new TripInfo();

            List<SearchOutputSegmentInfo> segments = new ArrayList<>();

            for (int i = 0; i < trip.size(); i++) {
                segments.add(GeneratorUtils.generateSearchOutputSegmentInfoFromSlice(
                        slicedSegments.get(i),
                        trip.get(i),
                        startCalendar
                ));
            }

            tripInfo.setSegments(segments);
            tripInfoList.add(tripInfo);
        }

        return tripInfoList;
    }
}
