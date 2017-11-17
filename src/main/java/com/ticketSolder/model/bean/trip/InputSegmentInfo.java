package com.ticketSolder.model.bean.trip;

import lombok.Data;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class InputSegmentInfo {

    private String trainName;
    private boolean fast;
    private int year;
    private int month;
    private int day;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private char startStation;
    private char endStation;
    private double price;
}
