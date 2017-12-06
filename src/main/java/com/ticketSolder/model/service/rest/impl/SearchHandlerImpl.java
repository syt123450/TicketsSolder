package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.trip.*;
import com.ticketSolder.model.service.rest.SearchHandler;
import com.ticketSolder.model.service.rest.helper.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/16.
 */

@Service
@Profile("dev")
public class SearchHandlerImpl implements SearchHandler {

    @Autowired
    private SearchHelper searchHelper;

    @Override
    public TripSearchResult searchTripInfo(TripRequest tripRequest) {

        if (tripRequest.isRound()) {
            return searchRoundTrip(tripRequest);
        } else {
            return searchSingleTrip(tripRequest);
        }
    }

    private TripSearchResult searchSingleTrip(TripRequest tripRequest) {

        SingleTripRequest goTripRequest = new SingleTripRequest(
                tripRequest.isFast(),
                tripRequest.isNormal(),
                tripRequest.getGoYear(),
                tripRequest.getGoMonth(),
                tripRequest.getGoDay(),
                tripRequest.getGoHour(),
                tripRequest.getGoMinute(),
                tripRequest.getGoStartStation(),
                tripRequest.getGoEndStation(),
                tripRequest.getConnections(),
                tripRequest.isExactly()
        );

        TripSearchResult tripSearchResult = new TripSearchResult();
        tripSearchResult.setRound(false);
        tripSearchResult.setGoTripInfoAggregation(searchOneWay(goTripRequest));

        return tripSearchResult;
    }

    private TripSearchResult searchRoundTrip(TripRequest tripRequest) {

        SingleTripRequest goTripRequest = new SingleTripRequest(
                tripRequest.isFast(),
                tripRequest.isNormal(),
                tripRequest.getGoYear(),
                tripRequest.getGoMonth(),
                tripRequest.getGoDay(),
                tripRequest.getGoHour(),
                tripRequest.getGoMinute(),
                tripRequest.getGoStartStation(),
                tripRequest.getGoEndStation(),
                tripRequest.getConnections(),
                tripRequest.isExactly()
        );

        SingleTripRequest backTripRequest = new SingleTripRequest(
                tripRequest.isFast(),
                tripRequest.isNormal(),
                tripRequest.getBackYear(),
                tripRequest.getBackMonth(),
                tripRequest.getBackDay(),
                tripRequest.getBackHour(),
                tripRequest.getBackMinute(),
                tripRequest.getBackStartStation(),
                tripRequest.getBackEndStation(),
                tripRequest.getConnections(),
                tripRequest.isExactly()
        );

        TripSearchResult tripSearchResult = new TripSearchResult();
        tripSearchResult.setRound(true);
        tripSearchResult.setGoTripInfoAggregation(searchOneWay(goTripRequest));
        tripSearchResult.setBackTripInfoAggregation(searchOneWay(backTripRequest));

        return tripSearchResult;
    }

    private TripInfoAggregation searchOneWay(SingleTripRequest singleTripRequest) {

        BasicTripSearchRequest basicTripSearchRequest = new BasicTripSearchRequest(
                singleTripRequest.getYear(),
                singleTripRequest.getMonth(),
                singleTripRequest.getDay(),
                singleTripRequest.getHour(),
                singleTripRequest.getMinute(),
                singleTripRequest.getStartStation(),
                singleTripRequest.getEndStation(),
                singleTripRequest.getConnections(),
                singleTripRequest.isExactly()
        );

        TripInfoAggregation tripInfoAggregation = new TripInfoAggregation();

        if (singleTripRequest.isNormal() && !singleTripRequest.isFast()) {
            tripInfoAggregation.setNormal(true);
            tripInfoAggregation.setFast(false);
            tripInfoAggregation.setNormalTrainTrips(searchHelper.searchNormalTrainTrip(basicTripSearchRequest));
        } else if (!singleTripRequest.isNormal() && singleTripRequest.isFast()) {
            tripInfoAggregation.setNormal(false);
            tripInfoAggregation.setFast(true);
            tripInfoAggregation.setFastTrainTrips(searchHelper.searchFastTrainTrip(basicTripSearchRequest));
        } else {
            tripInfoAggregation.setNormal(true);
            tripInfoAggregation.setFast(true);
            tripInfoAggregation.setFastTrainTrips(searchHelper.searchBothTrainTrip(basicTripSearchRequest));
        }

        return tripInfoAggregation;
    }
}