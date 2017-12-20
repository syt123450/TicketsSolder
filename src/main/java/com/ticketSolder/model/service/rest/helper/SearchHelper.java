package com.ticketSolder.model.service.rest.helper;

import com.ticketSolder.model.bean.trip.BasicTripSearchRequest;
import com.ticketSolder.model.bean.trip.SearchOutputSegmentInfo;
import com.ticketSolder.model.bean.trip.SlicedSegment;
import com.ticketSolder.model.bean.trip.TripInfo;
import com.ticketSolder.model.dao.mysql.SearchDao;
import com.ticketSolder.model.domain.mysql.SearchResultUnit;
import com.ticketSolder.model.utils.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ss on 2017/11/22.
 */

@Component
public class SearchHelper {

    private Logger logger = Logger.getLogger(SearchHelper.class);

    @Autowired
    private SearchDao searchDao;

    public List<TripInfo> searchBothTrainTrip(BasicTripSearchRequest basicTripSearchRequest) {

        List<TripInfo> tripInfoList1 = searchNormalTrainTrip(basicTripSearchRequest);
        List<TripInfo> tripInfoList2 = searchFastTrainTrip(basicTripSearchRequest);

        tripInfoList1.addAll(tripInfoList2);

        return TailorUtils.curtailBothTrips(tripInfoList1);
    }

    public List<TripInfo> searchNormalTrainTrip(BasicTripSearchRequest basicTripSearchRequest) {

        logger.info("Search for normal trip.");

        logger.info(basicTripSearchRequest);

        //prepare for the parameter for search

        Calendar startCalendar = TimeUtils.getStartCalendarForSearch(basicTripSearchRequest);

        logger.info("Start calendar is: " + startCalendar.getTime());

        Date startDate = new Date(startCalendar.getTimeInMillis());
        Time startTime = new Time(startCalendar.getTimeInMillis());

        List<String> segments = GeneratorUtils.generateSegments(
                basicTripSearchRequest.getStartStation(),
                basicTripSearchRequest.getEndStation()
        );

        boolean direction = basicTripSearchRequest.getEndStation() - basicTripSearchRequest.getStartStation() > 0;

        //execute search to get the basic result

        List<SearchResultUnit> searchResultUnits = searchDao.search(startDate,
                startTime,
                basicTripSearchRequest.getStartStation(),
                basicTripSearchRequest.getEndStation(),
                segments,
                direction,
                basicTripSearchRequest.isExactly(),
                false);

        logger.info("Search result from mybatis is: " + searchResultUnits);

        //limit the result to 5
        List<SearchResultUnit> curtailedUnits = TailorUtils.curtailNormalTrips(searchResultUnits, startDate);

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

    public List<TripInfo> searchFastTrainTrip(BasicTripSearchRequest basicTripSearchRequest) {

        logger.info("Search for fast trip.");

        List<List<SlicedSegment>> slicedSegmentLists = SliceUtils.sliceTrip(
                basicTripSearchRequest.getStartStation(),
                basicTripSearchRequest.getEndStation()
        );

        logger.info("At least has " + slicedSegmentLists.get(0).size() + " segments.");
        logger.info("User want at most " + (basicTripSearchRequest.getConnections() + 1) + " segments.");

        if (slicedSegmentLists.get(0).size() > basicTripSearchRequest.getConnections() + 1) {
            logger.info("Do not exist fast combination that has segments less than given connections.");
            return new ArrayList<>();
        }

        Calendar startCalendar = TimeUtils.getStartCalendarForSearch(basicTripSearchRequest);
        logger.info("Start calendar is: " + startCalendar.getTime());

        Date startDate = new Date(startCalendar.getTimeInMillis());
        Time startTime = new Time(startCalendar.getTimeInMillis());

        List<TripInfo> tripInfoList = new ArrayList<>();

        for (List<SlicedSegment> slicedSegmentList: slicedSegmentLists) {

            List<List<SearchResultUnit>> resultLists = new ArrayList<>();

            for (int i = 0; i < slicedSegmentList.size(); i++) {

                boolean exactly;
                if (i == 0) {
                    exactly = basicTripSearchRequest.isExactly();
                } else {
                    exactly = false;
                }

                List<SearchResultUnit> searchResultUnits = searchDao.search(startDate,
                        startTime,
                        slicedSegmentList.get(i).getStartStation(),
                        slicedSegmentList.get(i).getEndStation(),
                        GeneratorUtils.generateSegments(
                                slicedSegmentList.get(i).getStartStation(),
                                slicedSegmentList.get(i).getEndStation()
                        ),
                        slicedSegmentList.get(i).getEndStation() - slicedSegmentList.get(i).getStartStation() > 0,
                        exactly,
                        slicedSegmentList.get(i).isFast());

                System.out.println(searchResultUnits);

                resultLists.add(searchResultUnits);
            }

            List<TripInfo> tempTripInfoList = FastResultCombiner.combine(basicTripSearchRequest, slicedSegmentList, resultLists);
            tripInfoList.addAll(tempTripInfoList);
        }

        return TailorUtils.curtailBothTrips(tripInfoList);
    }
}