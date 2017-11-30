package com.ticketSolder.model.service.rest.mock;

import com.ticketSolder.model.bean.transaction.*;
import com.ticketSolder.model.service.rest.TransactionHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/29.
 */

@Service
@Profile("mock")
public class MockTransactionHandler implements TransactionHandler {

    @Override
    public TransactionAggregation searchTransaction(String userName, String password) {
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