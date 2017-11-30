package com.ticketSolder.model.domain.mysql;

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
    private int passengers;

    public TransactionTableUnit(String userName, boolean round, int passengers) {
        this.userName = userName;
        this.round = round;
        this.passengers = passengers;
        this.transactionId = 0;
    }
}