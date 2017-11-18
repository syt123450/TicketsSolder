package com.ticketSolder.model.bean.transaction;

import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class TransactionAggregation {

    private boolean result;
    private List<BasicTransactionInfo> transactions;
    private String reason;
}