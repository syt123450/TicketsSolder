package com.ticketSolder.model.service.impl;

import com.ticketSolder.model.bean.RoundSearchResult;
import com.ticketSolder.model.bean.RoundTripRequest;
import com.ticketSolder.model.bean.SingleTripRequest;
import com.ticketSolder.model.dao.SearchDao;
import com.ticketSolder.model.domain.TripInfo;
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
    public List<TripInfo> searchTripInfo(SingleTripRequest singleTripRequest) {

        return null;
    }

    @Override
    public RoundSearchResult searchRoundTripInfo(RoundTripRequest roundTripRequest) {

        SingleTripRequest comeTripRequest = new SingleTripRequest(
                roundTripRequest.getComeYear(),
                roundTripRequest.getComeMonth(),
                roundTripRequest.getComeDay(),
                roundTripRequest.getComeTime(),
                roundTripRequest.getComeStartStation(),
                roundTripRequest.getComeEndStation()
                );

        SingleTripRequest backTripRequest = new SingleTripRequest(
                roundTripRequest.getBackYear(),
                roundTripRequest.getBackMonth(),
                roundTripRequest.getBackDay(),
                roundTripRequest.getBackTime(),
                roundTripRequest.getBackStartStation(),
                roundTripRequest.getBackEndStation()
        );

        RoundSearchResult roundSearchResult = new RoundSearchResult();
        roundSearchResult.setComeTripInfoList(searchTripInfo(comeTripRequest));
        roundSearchResult.setBackTripInfoList(searchTripInfo(backTripRequest));

        return roundSearchResult;
    }
}
