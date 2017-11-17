package com.ticketSolder.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by ss on 2017/11/16.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SegmentInsertionUnit {

    private long transactionId;
    private String trainName;
    private boolean fast;
    private Date day;
    private Time startTime;
    private Time endTime;
    private double price;
    private char startStation;
    private char endStation;
    private int segmentNumber;
}