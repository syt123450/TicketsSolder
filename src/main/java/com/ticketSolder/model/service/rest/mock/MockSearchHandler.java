package com.ticketSolder.model.service.rest.mock;

import com.ticketSolder.model.bean.trip.*;
import com.ticketSolder.model.service.rest.SearchHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/11/29.
 */

@Service
@Profile("mock")
public class MockSearchHandler implements SearchHandler {

    @Override
    public TripSearchResult searchTripInfo(TripRequest tripRequest) {

        TripSearchResult tripSearchResult = new TripSearchResult();

        tripSearchResult.setRound(false);

        TripInfoAggregation tripInfoAggregation = new TripInfoAggregation();
        tripInfoAggregation.setNormal(true);
        tripInfoAggregation.setFast(false);
        List<TripInfo> tripInfoList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            TripInfo tripInfo = new TripInfo();

            List<SearchOutputSegmentInfo> searchOutputSegmentInfoList = new ArrayList<>();

            SearchOutputSegmentInfo searchOutputSegmentInfo = new SearchOutputSegmentInfo(
                    "SB0615",
                    false,
                    "2017-11-30",
                    "06:15",
                    "2017-11-30",
                    "06:20",
                    'A',
                    'B',
                    1,
                    100
            );

            searchOutputSegmentInfoList.add(searchOutputSegmentInfo);

            tripInfo.setSegments(searchOutputSegmentInfoList);

            tripInfoList.add(tripInfo);
        }

        tripInfoAggregation.setNormalTrainTrips(tripInfoList);
        tripSearchResult.setGoTripInfoAggregation(tripInfoAggregation);

        return tripSearchResult;
    }
}
