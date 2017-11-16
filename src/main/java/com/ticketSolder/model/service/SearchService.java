package com.ticketSolder.model.service;

import com.ticketSolder.model.bean.trip.TripSearchResult;
import com.ticketSolder.model.bean.trip.TripRequest;

/**
 * Created by ss on 2017/11/16.
 */
public interface SearchService {

    TripSearchResult searchTripInfo(TripRequest tripRequest);
}