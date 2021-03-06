package com.ticketSolder.model.bean.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/11/16.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleTripRequest {

    private boolean fast;
    private boolean normal;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private char startStation;
    private char endStation;
    private int connections;
    private boolean exactly;
}
