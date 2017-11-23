package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.trip.BasicTripSearchRequest;
import com.ticketSolder.model.bean.trip.TripInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/11/22.
 */

@Component
public class SearchHelper {

    public List<TripInfo> searchNormalTrainTrip(BasicTripSearchRequest basicTripSearchRequest) {

        List<TripInfo> tripInfoList = new ArrayList<>();

        return null;
    }

    public List<TripInfo> searchFastTrainTrip(BasicTripSearchRequest basicTripSearchRequest) {

        return null;
    }
}
