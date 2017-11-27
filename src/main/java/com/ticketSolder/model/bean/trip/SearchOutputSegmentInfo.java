package com.ticketSolder.model.bean.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

/**
 * Created by ss on 2017/11/17.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchOutputSegmentInfo {

    private String trainName;
    private boolean fast;
    private String startDay;
    private String startTime;
    private String endDay;
    private String endTime;
    private char startStation;
    private char endStation;
    private int price;
    private int ticketsLeft;
}