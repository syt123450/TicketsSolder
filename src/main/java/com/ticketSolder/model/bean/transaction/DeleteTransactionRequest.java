package com.ticketSolder.model.bean.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/11/16.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTransactionRequest {

    private String userName;
    private String password;
    private long transactionId;
}
