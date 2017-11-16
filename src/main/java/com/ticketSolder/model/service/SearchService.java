package com.ticketSolder.model.service;

import com.ticketSolder.model.bean.RoundSearchResult;
import com.ticketSolder.model.bean.RoundTripRequest;
import com.ticketSolder.model.bean.SingleTripRequest;
import com.ticketSolder.model.domain.TripInfo;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */
public interface SearchService {

    List<TripInfo> searchTripInfo(SingleTripRequest singleTripRequest);

    RoundSearchResult searchRoundTripInfo(RoundTripRequest roundTripRequest);
}
