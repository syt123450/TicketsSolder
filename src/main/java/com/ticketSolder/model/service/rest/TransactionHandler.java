package com.ticketSolder.model.service.rest;

import com.ticketSolder.model.bean.transaction.*;

/**
 * Created by ss on 2017/11/16.
 */
public interface TransactionHandler {

    TransactionAggregation searchTransaction(String userName);

    TransactionCreationResult createTransaction(CreateTransactionRequest createTransactionRequest);

    TransactionDeletionResult deleteTransaction(DeleteTransactionRequest deleteTransactionRequest);
}
