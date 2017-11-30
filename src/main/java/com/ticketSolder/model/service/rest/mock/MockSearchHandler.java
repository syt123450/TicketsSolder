package com.ticketSolder.model.service.rest.mock;

import com.ticketSolder.model.bean.trip.TripRequest;
import com.ticketSolder.model.bean.trip.TripSearchResult;
import com.ticketSolder.model.service.rest.SearchHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/29.
 */

@Service
@Profile("mock")
public class MockSearchHandler implements SearchHandler {

    @Override
    public TripSearchResult searchTripInfo(TripRequest tripRequest) {
        return null;
    }
}
