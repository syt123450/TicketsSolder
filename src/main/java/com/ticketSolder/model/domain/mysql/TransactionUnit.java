package com.ticketSolder.model.domain.mysql;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;


/**
 * Created by ss on 2017/11/16.
 */

@Data
public class TransactionUnit {

    private long transactionId;
    private boolean canceled;
    private boolean round;
    private String userName;

    private int segmentNumber;
    private boolean go;

    private String trainName;
    private boolean fast;
    private Date day;
    private Time startTime;
    private Time endTime;
    private char startStation;
    private char endStation;
    private int price;
    private int passengers;
}