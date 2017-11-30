package com.ticketSolder.model.domain.mysql;

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
    private Date day;
    private Time startTime;
    private Time endTime;
    private int price;
    private char startStation;
    private char endStation;
    private int segmentNumber;
    private boolean fast;
    private boolean direction;
}