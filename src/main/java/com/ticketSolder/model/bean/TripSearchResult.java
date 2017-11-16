package com.ticketSolder.model.bean;

import lombok.Data;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class TripSearchResult {

    private boolean round;
    private TripInfoAggregation goTripInfoAggregation;
    private TripInfoAggregation backTripInfoAggregation;
}
