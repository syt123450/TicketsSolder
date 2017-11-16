package com.ticketSolder.model.bean.transaction;

import lombok.Data;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class DeleteTransactionRequest {

    private String userName;
    private String password;
    private long transactionId;
}
