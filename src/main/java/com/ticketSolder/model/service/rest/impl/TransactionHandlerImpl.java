package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.transaction.*;
import com.ticketSolder.model.domain.mysql.UserInfo;
import com.ticketSolder.model.service.rest.TransactionHandler;
import com.ticketSolder.model.service.rest.helper.TransactionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Service
public class TransactionHandlerImpl implements TransactionHandler {

    private static final String FAIL_REASON = "Transaction failed.";
    private static final String SEARCH_FAIL_REASON = "Failed to search transactions.";

    @Autowired
    private TransactionHelper transactionHelper;

    @Override
    public TransactionAggregation searchTransaction(String userName, String password) {

        UserInfo userInfo = new UserInfo(userName, password);
        TransactionAggregation aggregation = new TransactionAggregation();

        try {
            List<BasicTransactionInfo> basicTransactionInfos = transactionHelper.searchHelper(userInfo);
            aggregation.setResult(true);
            aggregation.setTransactions(basicTransactionInfos);
        } catch (Exception e) {
            e.printStackTrace();
            aggregation.setResult(false);
            aggregation.setReason(SEARCH_FAIL_REASON);
        }

        return aggregation;
    }

    @Override
    public TransactionCreationResult createTransaction(CreateTransactionRequest createTransactionRequest) {

        UserInfo userInfo = new UserInfo(createTransactionRequest.getUserName(),
                createTransactionRequest.getPassword());
        TransactionCreationResult transactionCreationResult = new TransactionCreationResult();

        try {
            transactionHelper.creationHelper(userInfo, createTransactionRequest);
            transactionCreationResult.setResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            transactionCreationResult.setResult(false);
            transactionCreationResult.setReason(FAIL_REASON);
        }

        return transactionCreationResult;
    }

    @Override
    public TransactionDeletionResult deleteTransaction(DeleteTransactionRequest deleteTransactionRequest) {

        UserInfo userInfo = new UserInfo(deleteTransactionRequest.getUserName(),
                deleteTransactionRequest.getPassword());
        TransactionDeletionResult transactionDeletionResult = new TransactionDeletionResult();

        try {
            transactionHelper.deleteHelper(userInfo, deleteTransactionRequest);
            transactionDeletionResult.setResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            transactionDeletionResult.setResult(false);
            transactionDeletionResult.setReason(FAIL_REASON);
        }

        return transactionDeletionResult;
    }
}