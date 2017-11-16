package com.ticketSolder.model.bean.trip;

import lombok.Data;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class TripRequest {

    private boolean round;
    private boolean fast;
    private boolean normal;

    private int goYear;
    private int goMonth;
    private int goDay;
    private double goTime;
    private char goStartStation;
    private char goEndStation;

    private int backYear;
    private int backMonth;
    private int backDay;
    private double backTime;
    private char backStartStation;
    private char backEndStation;
}