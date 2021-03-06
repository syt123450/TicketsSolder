package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.transaction.*;
import com.ticketSolder.model.dao.mysql.UserDao;
import com.ticketSolder.model.domain.mysql.UserInfo;
import com.ticketSolder.model.service.rest.TransactionHandler;
import com.ticketSolder.model.service.rest.helper.TransactionHelper;
import com.ticketSolder.model.utils.EmailUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Service
@Profile("dev")
public class TransactionHandlerImpl implements TransactionHandler {

    private static final String FAIL_REASON = "Transaction failed.";
    private static final String SEARCH_FAIL_REASON = "Failed to search transactions.";

    private Logger logger = Logger.getLogger(TransactionHandlerImpl.class);

    @Autowired
    private TransactionHelper transactionHelper;
    @Autowired
    private UserDao userDao;

    @Override
    public TransactionAggregation searchTransaction(String userName, String password) {

        UserInfo userInfo = new UserInfo(userName, password);
        TransactionAggregation aggregation = new TransactionAggregation();

        try {
            List<BasicTransactionInfo> basicTransactionInfos = transactionHelper.searchHelper(userInfo);
            aggregation.setResult(true);
            aggregation.setTransactions(basicTransactionInfos);
        } catch (Exception e) {
            logger.info("Failed to search transaction because: " + e.getMessage());
            aggregation.setResult(false);
            aggregation.setReason(e.getMessage());
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
            String emailAddress = userDao.getEmailByName(userInfo.getUserName());
            EmailUtils.sendSuccessEmail(createTransactionRequest, emailAddress);
        } catch (Exception e) {
            logger.info("Failed to create transaction because: " + e.getMessage());
            transactionCreationResult.setResult(false);
            transactionCreationResult.setReason(e.getMessage());
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
            String emailAddress = userDao.getEmailByName(userInfo.getUserName());
            EmailUtils.sendCancelEmail(emailAddress);
        } catch (Exception e) {
            logger.info("Failed to cancel transaction because: " + e.getMessage());
            transactionDeletionResult.setResult(false);
            transactionDeletionResult.setReason(e.getMessage());
        }

        return transactionDeletionResult;
    }
}