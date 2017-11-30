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
    private int price;
}
