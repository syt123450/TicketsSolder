package com.ticketSolder.model.service.rest;

import com.ticketSolder.model.bean.trip.TripSearchResult;
import com.ticketSolder.model.bean.trip.TripRequest;

/**
 * Created by ss on 2017/11/16.
 */
public interface SearchHandler {

    TripSearchResult searchTripInfo(TripRequest tripRequest);
}