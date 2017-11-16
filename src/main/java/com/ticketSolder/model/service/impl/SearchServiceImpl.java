package com.ticketSolder.model.service.impl;

import com.ticketSolder.model.bean.*;
import com.ticketSolder.model.dao.SearchDao;
import com.ticketSolder.model.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

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
                tripRequest.getComeYear(),
                tripRequest.getComeMonth(),
                tripRequest.getComeDay(),
                tripRequest.getComeTime(),
                tripRequest.getComeStartStation(),
                tripRequest.getComeEndStation()
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
                tripRequest.getComeYear(),
                tripRequest.getComeMonth(),
                tripRequest.getComeDay(),
                tripRequest.getComeTime(),
                tripRequest.getComeStartStation(),
                tripRequest.getComeEndStation()
        );

        SingleTripRequest backTripRequest = new SingleTripRequest(
                tripRequest.isFast(),
                tripRequest.isNormal(),
                tripRequest.getBackYear(),
                tripRequest.getBackMonth(),
                tripRequest.getBackDay(),
                tripRequest.getBackTime(),
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

        BasicTripSearchInfo basicTripSearchInfo = new BasicTripSearchInfo(
                singleTripRequest.getYear(),
                singleTripRequest.getMonth(),
                singleTripRequest.getDay(),
                singleTripRequest.getTime(),
                singleTripRequest.getStartStation(),
                singleTripRequest.getEndStation()
        );

        TripInfoAggregation tripInfoAggregation = new TripInfoAggregation();

        if (singleTripRequest.isNormal()) {
            tripInfoAggregation.setNormalTrainTrips(searchNormalTrainTrip(basicTripSearchInfo));
        }

        if (singleTripRequest.isFast()) {
            tripInfoAggregation.setFastTrainTrips(searchFastTrainTrip(basicTripSearchInfo));
        }

        return tripInfoAggregation;
    }

    private List<NormalTripInfo> searchNormalTrainTrip(BasicTripSearchInfo basicTripSearchInfo) {

        return null;
    }

    private List<FastTripInfo> searchFastTrainTrip(BasicTripSearchInfo basicTripSearchInfo) {

        return null;
    }
}
