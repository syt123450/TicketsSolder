package com.ticketSolder.model.bean;

import lombok.Data;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class NormalTripInfo {

    private String trainName;
    private int year;
    private int month;
    private int day;
    private double startTime;
    private double endTime;
    private char startStation;
    private char endStation;
}