package com.ticketSolder.model.bean.trip;

import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class TripInfo {

    private List<OutputSegmentInfo> segments;
}
