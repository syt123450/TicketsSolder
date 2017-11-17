package com.ticketSolder.model.bean.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/11/17.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputSegmentInfo {

    private String trainName;
    private boolean fast;
    private String day;
    private String startTime;
    private String endTime;
    private char startStation;
    private char endStation;
    private double price;
}
