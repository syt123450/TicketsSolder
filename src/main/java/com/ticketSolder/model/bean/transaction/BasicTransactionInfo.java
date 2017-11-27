package com.ticketSolder.model.bean.transaction;

import com.ticketSolder.model.bean.trip.SearchOutputSegmentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicTransactionInfo {

    private String userName;
    private long transactionId;
    private boolean canceled;
    private boolean round;
    private List<TransactionOutputSegmentInfo> goSegments;
    private List<TransactionOutputSegmentInfo> backSegments;

    public BasicTransactionInfo(String userName, long transactionId, boolean canceled, boolean round) {
        this.userName = userName;
        this.transactionId = transactionId;
        this.canceled = canceled;
        this.round = round;
        this.goSegments = new ArrayList<>();
        this.backSegments = new ArrayList<>();
    }
}