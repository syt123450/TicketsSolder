package com.ticketSolder.model.domain.mysql;

import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/11/27.
 */

@Data
public class CanceledTransactionInfo {

    private long transactionId;
    private String userName;
    private String password;

    private boolean round;
    private int passengers;

    private List<CanceledSegmentInfo> canceledSegmentInfoList;
}