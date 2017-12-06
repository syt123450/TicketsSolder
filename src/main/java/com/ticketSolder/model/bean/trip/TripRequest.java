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
public class TripRequest {

    private boolean round;
    private boolean fast;
    private boolean normal;

    private int goYear;
    private int goMonth;
    private int goDay;
    private int goHour;
    private int goMinute;
    private char goStartStation;
    private char goEndStation;

    private int backYear;
    private int backMonth;
    private int backDay;
    private int backHour;
    private int backMinute;
    private char backStartStation;
    private char backEndStation;

    private int connections;
    private boolean exactly;
}