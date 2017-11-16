package com.ticketSolder.model.bean;

import com.ticketSolder.model.domain.TripInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class RoundSearchResult {

    private List<TripInfo> comeTripInfoList;
    private List<TripInfo> backTripInfoList;
}
