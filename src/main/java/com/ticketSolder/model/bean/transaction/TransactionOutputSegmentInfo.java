package com.ticketSolder.model.bean.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/11/24.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionOutputSegmentInfo {

    private String trainName;
    private boolean fast;
    private String startDay;
    private String startTime;
    private String endDay;
    private String endTime;
    private char startStation;
    private char endStation;
    private int price;
}
