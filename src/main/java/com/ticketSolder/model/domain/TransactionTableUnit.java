package com.ticketSolder.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/11/16.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTableUnit {

    private String userName;
    private long transactionId;
    private boolean round;

    public TransactionTableUnit(String userName, boolean round) {
        this.userName = userName;
        this.round = round;
        this.transactionId = 0;
    }
}