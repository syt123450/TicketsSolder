package com.ticketSolder.model.service.rest.helper;

import com.ticketSolder.model.bean.cancel.CancelRequest;
import com.ticketSolder.model.bean.cancel.CanceledTransactionInfo;
import com.ticketSolder.model.bean.cancel.UnbookedUser;
import com.ticketSolder.model.bean.transaction.CreateTransactionRequest;
import com.ticketSolder.model.bean.transaction.TransactionCreationResult;
import com.ticketSolder.model.dao.mysql.CancelDao;
import com.ticketSolder.model.service.rest.TransactionHandler;
import com.ticketSolder.model.utils.GeneratorUtils;
import com.ticketSolder.model.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ss on 2017/11/27.
 */

@Component
public class CancelHelper {

    private static final String TIME_LATE_MESSAGE = "Failed to cancel the train " +
            "as cancel must happen no later than 3 three hours before the original train Starts.";
    private static final String FAIL_CANCEL_TRAIN_MESSAGE = "Failed to cancel the original train.";
    private static final String FAIL_FIND_RELEVANT_TRANSACTION_MESSAGE = "Failed to find relevant transactions.";
    private static final String FAIL_CANCEL_TICKETS_MESSAGE = "Failed to cancel the exists tickets";
    private static final String FAIL_REBOOK_MESSAGE = "Failed to rebook for users.";

    @Autowired
    private CancelDao cancelDao;
    @Autowired
    private TransactionHandler transactionHandler;

    @Transactional
    public List<UnbookedUser> cancel(CancelRequest cancelRequest) throws Exception {

        Date date = TimeUtils.getSQLDate(
                cancelRequest.getYear(),
                cancelRequest.getMonth(),
                cancelRequest.getDay()
        );

        validateTime(cancelRequest);

        List<CanceledTransactionInfo> canceledTransactionInfoList =
                findRelevantTransaction(cancelRequest.getTrainName(), date);

        cancelTrain(cancelRequest.getTrainName(), date);

        List<Long> transactionIds = GeneratorUtils.generateTransactionIds(canceledTransactionInfoList);
        cancelExistTickets(transactionIds);

        List<CreateTransactionRequest> createTransactionRequestList =
                GeneratorUtils.generateCreateTransactionRequestList(canceledTransactionInfoList);

        return rebook(createTransactionRequestList);
    }

    private void validateTime(CancelRequest cancelRequest) throws Exception {

        Calendar timeNow = Calendar.getInstance();
        Calendar trainStartCalendar = Calendar.getInstance();
        trainStartCalendar.set(Calendar.YEAR, cancelRequest.getYear());
        trainStartCalendar.set(Calendar.MONTH, cancelRequest.getMonth() - 1);
        trainStartCalendar.set(Calendar.DAY_OF_MONTH, cancelRequest.getDay());
        Time startTime = cancelDao.getStartTime(cancelRequest.getTrainName());
        trainStartCalendar.setTime(startTime);

        long differenceHour = (trainStartCalendar.getTimeInMillis() - timeNow.getTimeInMillis()) / 1000 / 60 / 60;

        if (differenceHour < 3) {
            throw new Exception(TIME_LATE_MESSAGE);
        }
    }

    private void cancelTrain(String trainName, Date date) throws Exception {

        try {
            cancelDao.cancelTrain(trainName, date);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(FAIL_CANCEL_TRAIN_MESSAGE);
        }
    }

    private List<CanceledTransactionInfo> findRelevantTransaction(String trainName, Date date)
            throws Exception {

        try {
            return cancelDao.findTransactions(trainName, date);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(FAIL_FIND_RELEVANT_TRANSACTION_MESSAGE);
        }
    }

    private void cancelExistTickets(List<Long> transactionIds) throws Exception {

        try {
            cancelDao.cancelTickets(transactionIds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(FAIL_CANCEL_TICKETS_MESSAGE);
        }
    }

    private List<UnbookedUser> rebook(List<CreateTransactionRequest> createTransactionRequestList) throws Exception {

        List<UnbookedUser> unbookedUserList = new ArrayList<>();

        try {

            for (CreateTransactionRequest createTransactionRequest: createTransactionRequestList) {
                TransactionCreationResult transactionCreationResult =
                        transactionHandler.createTransaction(createTransactionRequest);
                if (!transactionCreationResult.isResult()) {
                    UnbookedUser unbookedUser = new UnbookedUser(
                            createTransactionRequest.getUserName()
                    );
                    unbookedUserList.add(unbookedUser);
                }
            }

            return unbookedUserList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(FAIL_REBOOK_MESSAGE);
        }
    }
}