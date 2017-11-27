package com.ticketSolder.model.bean.cancel;

import lombok.Data;

/**
 * Created by ss on 2017/11/27.
 */

@Data
public class CanceledTransactionInfo {

    private long transactionId;
    private long userId;

}
