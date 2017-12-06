package com.ticketSolder.model.service.rest.helper;

import com.ticketSolder.model.bean.trip.BasicTripSearchRequest;
import com.ticketSolder.model.bean.trip.SearchOutputSegmentInfo;
import com.ticketSolder.model.bean.trip.TripInfo;
import com.ticketSolder.model.dao.mysql.SearchDao;
import com.ticketSolder.model.domain.mysql.SearchResultUnit;
import com.ticketSolder.model.utils.ComparatorUtils;
import com.ticketSolder.model.utils.GeneratorUtils;
import com.ticketSolder.model.utils.PriceUtils;
import com.ticketSolder.model.utils.TimeUtils;
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

    public List<TripInfo> searchNormalTrainTrip(BasicTripSearchRequest basicTripSearchRequest) {

        logger.info("Search for normal trip.");

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
                basicTripSearchRequest.isExactly());

        logger.info("Search result from mybatis is: " + searchResultUnits);

        //limit the result to 5
        List<SearchResultUnit> curtailedUnits = curtailNormalTrips(searchResultUnits, startDate);

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



        return null;
    }

    public List<TripInfo> searchBothTrainTrip(BasicTripSearchRequest basicTripSearchRequest) {


        return null;
    }

    private List<SearchResultUnit> curtailNormalTrips(List<SearchResultUnit> searchResultUnits, Date startDate) {

        searchResultUnits.sort(ComparatorUtils.getSearchResultUnitComparator(startDate));

        if (searchResultUnits.size() < 5) {
            return searchResultUnits;
        } else {
            return searchResultUnits.subList(0, 5);
        }
    }
}