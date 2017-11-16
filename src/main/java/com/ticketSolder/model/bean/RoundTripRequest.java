package com.ticketSolder.model.bean;

import lombok.Data;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class RoundTripRequest {

    private int comeYear;
    private int comeMonth;
    private int comeDay;
    private double comeTime;
    private char comeStartStation;
    private char comeEndStation;

    private int backYear;
    private int backMonth;
    private int backDay;
    private double backTime;
    private char backStartStation;
    private char backEndStation;
}
