package com.ticketSolder.model.service.rest.helper;

import com.ticketSolder.model.bean.trip.BasicTripSearchRequest;
import com.ticketSolder.model.bean.trip.SearchOutputSegmentInfo;
import com.ticketSolder.model.bean.trip.TripInfo;
import com.ticketSolder.model.dao.mysql.SearchDao;
import com.ticketSolder.model.domain.mysql.SearchResultUnit;
import com.ticketSolder.model.utils.GeneratorUtils;
import com.ticketSolder.model.utils.PriceUtils;
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

    @Autowired
    private SearchDao searchDao;

    public List<TripInfo> searchNormalTrainTrip(BasicTripSearchRequest basicTripSearchRequest) {

        List<TripInfo> tripInfoList = new ArrayList<>();

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.YEAR, basicTripSearchRequest.getYear());
        startCalendar.set(Calendar.MONTH, basicTripSearchRequest.getMonth());
        startCalendar.set(Calendar.DAY_OF_MONTH, basicTripSearchRequest.getDay());
        startCalendar.set(Calendar.HOUR_OF_DAY, basicTripSearchRequest.getHour());
        startCalendar.set(Calendar.MINUTE, basicTripSearchRequest.getMinute());
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);

        Date startDate = new Date(startCalendar.getTimeInMillis());
        Time startTime = new Time(startCalendar.getTimeInMillis());

        boolean direction = basicTripSearchRequest.getEndStation() - basicTripSearchRequest.getStartStation() > 0;

        List<String> segments = GeneratorUtils.generateSegments(
                basicTripSearchRequest.getStartStation(),
                basicTripSearchRequest.getEndStation()
        );

        List<SearchResultUnit> searchResultUnits = searchDao.search(startDate,
                startTime,
                basicTripSearchRequest.getStartStation(),
                basicTripSearchRequest.getEndStation(),
                segments,
                direction);

        SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-DD");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");

        Calendar trainStart = Calendar.getInstance();
        Calendar trainEnd = Calendar.getInstance();

        for (int i = 0; i < searchResultUnits.size(); i++) {
            TripInfo tripInfo = new TripInfo();
            List<SearchOutputSegmentInfo> searchOutputSegmentInfoList = new ArrayList<>();

            Time trainStartTime = searchResultUnits.get(i).getStartTime();
            Time trainEndTime = searchResultUnits.get(i).getEndTime();
            trainStart.setTime(trainStartTime);
            trainEnd.setTime(trainEndTime);
            trainEnd.add(Calendar.MINUTE, -3);

            if (trainStart.getTimeInMillis() > trainEnd.getTimeInMillis()) {
                trainEnd.add(Calendar.DAY_OF_MONTH, 1);
            }

            SearchOutputSegmentInfo searchOutputSegmentInfo = new SearchOutputSegmentInfo();

            searchOutputSegmentInfo.setTrainName(searchResultUnits.get(i).getTrainName());
            searchOutputSegmentInfo.setFast(searchResultUnits.get(i).isFast());

            searchOutputSegmentInfo.setStartDay(dateFormatter.format(startCalendar));
            searchOutputSegmentInfo.setStartTime(timeFormatter.format(trainStartTime));
            searchOutputSegmentInfo.setEndDay(dateFormatter.format(trainEnd));
            searchOutputSegmentInfo.setEndTime(timeFormatter.format(trainEndTime));

            searchOutputSegmentInfo.setStartStation(basicTripSearchRequest.getStartStation());
            searchOutputSegmentInfo.setEndStation(basicTripSearchRequest.getEndStation());
            searchOutputSegmentInfo.setPrice(PriceUtils.getPrice(false,
                    basicTripSearchRequest.getStartStation(),
                    basicTripSearchRequest.getEndStation()));
            searchOutputSegmentInfo.setTicketsLeft(searchResultUnits.get(i).getTicketsLeft());

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
