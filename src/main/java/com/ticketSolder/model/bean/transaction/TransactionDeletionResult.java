package com.ticketSolder.model.bean.transaction;

import lombok.Data;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class TransactionDeletionResult {

    private boolean result;
    private String reason;
}
