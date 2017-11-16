package com.ticketSolder.model.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class TripInfoAggregation {

    private List<NormalTripInfo> normalTrainTrips;
    private List<FastTripInfo> fastTrainTrips;
}
