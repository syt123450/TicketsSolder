package com.ticketSolder.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/11/16.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicTripSearchInfo {

    private int year;
    private int month;
    private int day;
    private double time;
    private char startStation;
    private char endStation;
}
