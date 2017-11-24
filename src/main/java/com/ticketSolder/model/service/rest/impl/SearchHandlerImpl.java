package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.trip.*;
import com.ticketSolder.model.service.rest.SearchHandler;
import com.ticketSolder.model.service.rest.helper.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/16.
 */

@Service
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
                tripRequest.getGoEndStation()
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
                tripRequest.getGoEndStation()
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
                tripRequest.getBackEndStation()
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
                singleTripRequest.getEndStation()
        );

        TripInfoAggregation tripInfoAggregation = new TripInfoAggregation();

        if (singleTripRequest.isNormal()) {
            tripInfoAggregation.setNormal(true);
            tripInfoAggregation.setNormalTrainTrips(searchHelper.searchNormalTrainTrip(basicTripSearchRequest));
        }

        if (singleTripRequest.isFast()) {
            tripInfoAggregation.setNormal(true);
            tripInfoAggregation.setFastTrainTrips(searchHelper.searchFastTrainTrip(basicTripSearchRequest));
        }

        return tripInfoAggregation;
    }
}