package com.ticketSolder.model.service.rest.helper;

import com.ticketSolder.model.bean.trip.BasicTripSearchRequest;
import com.ticketSolder.model.bean.trip.SearchOutputSegmentInfo;
import com.ticketSolder.model.bean.trip.TripInfo;
import com.ticketSolder.model.dao.mysql.SearchDao;
import com.ticketSolder.model.domain.mysql.SearchResultUnit;
import com.ticketSolder.model.utils.GeneratorUtils;
import com.ticketSolder.model.utils.PriceUtils;
import com.ticketSolder.model.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ss on 2017/11/22.
 */

@Component
public class SearchHelper {

    private static final String DATE_FORMAT = "yyyy-MM-DD";
    private static final String TIME_FORMAT = "HH:mm";

    @Autowired
    private SearchDao searchDao;

    public List<TripInfo> searchNormalTrainTrip(BasicTripSearchRequest basicTripSearchRequest) {

        //prepare for the parameter for search

        Calendar startCalendar = TimeUtils.getStartCalendarForSearch(basicTripSearchRequest);

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
                direction);

        //format the result

        List<TripInfo> tripInfoList = new ArrayList<>();

        SimpleDateFormat dateFormatter=new SimpleDateFormat(DATE_FORMAT);
        SimpleDateFormat timeFormatter = new SimpleDateFormat(TIME_FORMAT);

        Calendar trainStart = Calendar.getInstance();
        Calendar trainEnd = Calendar.getInstance();

        for (SearchResultUnit searchResultUnit: searchResultUnits) {

            TripInfo tripInfo = new TripInfo();
            List<SearchOutputSegmentInfo> searchOutputSegmentInfoList = new ArrayList<>();

            trainStart.setTime(startDate);

            Time trainStartTime = searchResultUnit.getStartTime();
            Time trainEndTime = searchResultUnit.getEndTime();
            trainStart.setTime(trainStartTime);
            trainEnd = TimeUtils.getEndCalendar(startDate, trainEndTime, trainStart, trainEnd);

            SearchOutputSegmentInfo searchOutputSegmentInfo = new SearchOutputSegmentInfo(
                    searchResultUnit.getTrainName(),
                    searchResultUnit.isFast(),
                    dateFormatter.format(startCalendar),
                    timeFormatter.format(startCalendar),
                    dateFormatter.format(trainEnd),
                    timeFormatter.format(trainEnd),
                    basicTripSearchRequest.getStartStation(),
                    basicTripSearchRequest.getEndStation(),
                    PriceUtils.getPrice(false,
                            basicTripSearchRequest.getStartStation(),
                            basicTripSearchRequest.getEndStation()),
                    searchResultUnit.getTicketsLeft()
            );

            searchOutputSegmentInfoList.add(searchOutputSegmentInfo);
            tripInfo.setSegments(searchOutputSegmentInfoList);

            tripInfoList.add(tripInfo);
        }

        return tripInfoList;
    }

    public List<TripInfo> searchFastTrainTrip(BasicTripSearchRequest basicTripSearchRequest) {

        return null;
    }
}
