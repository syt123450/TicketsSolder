package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.transaction.*;
import com.ticketSolder.model.dao.TransactionDao;
import com.ticketSolder.model.service.rest.TransactionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/16.
 */

@Service
public class TransactionHandlerImpl implements TransactionHandler {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public TransactionAggregation searchTransaction(String userName) {
        return null;
    }

    @Override
    public TransactionCreationResult createTransaction(CreateTransactionRequest createTransactionRequest) {
        return null;
    }

    @Override
    public TransactionDeletionResult deleteTransaction(DeleteTransactionRequest deleteTransactionRequest) {
        return null;
    }
}