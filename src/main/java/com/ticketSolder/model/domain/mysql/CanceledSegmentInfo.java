package com.ticketSolder.model.domain.mysql;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by ss on 2017/11/29.
 */

@Data
public class CanceledSegmentInfo {

    private Date startDate;
    private Time startTime;
    private char startStation;
    private char endStation;
    private int segmentNumber;
    private boolean fast;
}
