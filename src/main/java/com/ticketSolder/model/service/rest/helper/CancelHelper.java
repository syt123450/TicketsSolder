package com.ticketSolder.model.service.rest.helper;

import com.ticketSolder.model.bean.cancel.CancelRequest;
import com.ticketSolder.model.domain.mysql.CanceledTransactionInfo;
import com.ticketSolder.model.bean.cancel.RebookRequest;
import com.ticketSolder.model.bean.cancel.CanceledUser;
import com.ticketSolder.model.bean.transaction.CreateTransactionRequest;
import com.ticketSolder.model.bean.transaction.TransactionCreationResult;
import com.ticketSolder.model.bean.trip.TripSearchResult;
import com.ticketSolder.model.dao.mysql.CancelDao;
import com.ticketSolder.model.service.rest.SearchHandler;
import com.ticketSolder.model.service.rest.TransactionHandler;
import com.ticketSolder.model.utils.EmailUtils;
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
    private static final String ALREADY_CANCELED_MESSAGE = "The train you want to cancel has already be canceled.";
    private static final String FAIL_FIND_RELEVANT_TRANSACTION_MESSAGE = "Failed to find relevant transactions.";
    private static final String FAIL_CANCEL_TICKETS_MESSAGE = "Failed to cancel the exists tickets";
    private static final String FAIL_FIND_TICKETS_MESSAGE = "Failed to find ticket for user.";
    private static final String FAIL_REBOOK_MESSAGE = "Failed to rebook for users.";

    @Autowired
    private CancelDao cancelDao;
    @Autowired
    private SearchHandler searchHandler;
    @Autowired
    private TransactionHandler transactionHandler;

    @Transactional
    public List<CanceledUser> cancel(CancelRequest cancelRequest) throws Exception {

        Date date = TimeUtils.getSQLDate(
                cancelRequest.getYear(),
                cancelRequest.getMonth(),
                cancelRequest.getDay()
        );

        //validate whether time is three hour ahead of aboard
        validateTime(cancelRequest);

        //cancel the train
        cancelTrain(cancelRequest.getTrainName(), date);

        //get transaction need to be canceled due to cancel the train
        List<CanceledTransactionInfo> canceledTransactionInfoList =
                findRelevantTransaction(cancelRequest.getTrainName(), date);

        //get all transaction need to be canceled
        List<Long> transactionIds = GeneratorUtils.generateTransactionIds(canceledTransactionInfoList);
        cancelExistTickets(transactionIds);

        //create list to store the user whose transaction can not be created
        List<CanceledUser> canceledUserList = new ArrayList<>();
        List<CanceledUser> rebookUserList = new ArrayList<>();

        //prepare search requests for search
        List<RebookRequest> rebookRequests = GeneratorUtils.generateTripRequestList(canceledTransactionInfoList);

        //search the tickets, prepare transaction request list for create new transaction for user whose transaction been canceled
        List<CreateTransactionRequest> createTransactionRequestList =
                searchForRebookTickets(rebookRequests, canceledUserList);

        //rebook tickets for users whose transaction been canceled due to cancel of the train
        rebook(createTransactionRequestList, canceledUserList, rebookUserList);

        //send email to user to notify that the train has been canceled
        sendMailToUser(canceledUserList, rebookUserList);

        //return the user list whose transaction can not be rebook
        return canceledUserList;
    }

    private void validateTime(CancelRequest cancelRequest) throws Exception {

        Calendar timeNow = Calendar.getInstance();

        Calendar trainStartCalendar = Calendar.getInstance();

        Time startTime = cancelDao.getStartTime(cancelRequest.getTrainName(),
                GeneratorUtils.generateDirection(cancelRequest.getTrainName()),
                GeneratorUtils.generateFast(cancelRequest.getTrainName()));
        trainStartCalendar.setTime(startTime);

        trainStartCalendar.set(Calendar.YEAR, cancelRequest.getYear());
        trainStartCalendar.set(Calendar.MONTH, cancelRequest.getMonth() - 1);
        trainStartCalendar.set(Calendar.DAY_OF_MONTH, cancelRequest.getDay());

        long differenceHour = (trainStartCalendar.getTimeInMillis() - timeNow.getTimeInMillis()) / 1000 / 60 / 60;

        if (differenceHour < 3) {
            throw new Exception(TIME_LATE_MESSAGE);
        }
    }

    private void cancelTrain(String trainName, Date date) throws Exception {

        try {
            int canceledTrainNumber = cancelDao.cancelTrain(trainName, date);
            if (canceledTrainNumber != 1) {
                throw new Exception(ALREADY_CANCELED_MESSAGE);
            }
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

    private List<CreateTransactionRequest> searchForRebookTickets(
            List<RebookRequest> rebookRequests,
            List<CanceledUser> canceledUserList) throws Exception {

        List<CreateTransactionRequest> createTransactionRequestList = new ArrayList<>();

        try {

            for (RebookRequest rebookRequest : rebookRequests) {
                TripSearchResult tripSearchResult = searchHandler.searchTripInfo(rebookRequest.getTripRequest());

                //if the go trip result size is 0 or request is round and back trip size is 0, define no ticket for this user
                if (tripSearchResult.getGoTripInfoAggregation().getNormalTrainTrips().size() == 0 ||
                        (rebookRequest.getTripRequest().isRound()
                                && tripSearchResult.getBackTripInfoAggregation().getNormalTrainTrips().size() == 0)) {
                    CanceledUser canceledUser = new CanceledUser();
                    canceledUser.setUserName(rebookRequest.getUserName());
                    canceledUserList.add(canceledUser);
                } else {
                    CreateTransactionRequest createTransactionRequest =
                            GeneratorUtils.generateCreationRequestFromSearch(tripSearchResult, rebookRequest);
                    createTransactionRequestList.add(createTransactionRequest);
                }
            }

            return createTransactionRequestList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(FAIL_FIND_TICKETS_MESSAGE);
        }
    }

    private void rebook(
            List<CreateTransactionRequest> createTransactionRequestList,
            List<CanceledUser> canceledUserList,
            List<CanceledUser> rebookUserList) throws Exception {

        try {

            for (CreateTransactionRequest createTransactionRequest : createTransactionRequestList) {
                TransactionCreationResult transactionCreationResult =
                        transactionHandler.createTransaction(createTransactionRequest);

                CanceledUser canceledUser = new CanceledUser();
                canceledUser.setUserName(createTransactionRequest.getUserName());

                if (!transactionCreationResult.isResult()) {
                    canceledUserList.add(canceledUser);
                } else {
                    rebookUserList.add(canceledUser);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(FAIL_REBOOK_MESSAGE);
        }
    }

    private void sendMailToUser(List<CanceledUser> canceledUserList,
                                List<CanceledUser> rebookUserList) {

        EmailUtils.sendFailedRebookEmail(canceledUserList);
        EmailUtils.sendRebookEmail(rebookUserList);
    }
}