package com.ticketSolder.model.bean.trip;

import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class TripInfoAggregation {

    private boolean normal;
    private boolean fast;
    private List<TripInfo> normalTrainTrips;
    private List<TripInfo> fastTrainTrips;
}
