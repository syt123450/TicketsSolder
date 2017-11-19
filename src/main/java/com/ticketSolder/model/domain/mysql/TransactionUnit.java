package com.ticketSolder.model.domain.mysql;

import lombok.Data;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class TransactionUnit {

    private long transactionId;
    private boolean round;
    private String userName;

    private int segmentNumber;
    private boolean go;

    private String trainName;
    private boolean fast;
    private String day;
    private String startTime;
    private String endTime;
    private char startStation;
    private char endStation;
    private double price;

}