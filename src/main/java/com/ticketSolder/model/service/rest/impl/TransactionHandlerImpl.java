package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.transaction.*;
import com.ticketSolder.model.bean.trip.InputSegmentInfo;
import com.ticketSolder.model.bean.trip.OutputSegmentInfo;
import com.ticketSolder.model.dao.TransactionDao;
import com.ticketSolder.model.domain.SegmentInsertionUnit;
import com.ticketSolder.model.domain.SegmentStationInfo;
import com.ticketSolder.model.domain.TransactionTableUnit;
import com.ticketSolder.model.domain.TransactionUnit;
import com.ticketSolder.model.service.rest.TransactionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

/**
 * Created by ss on 2017/11/16.
 */

@Service
public class TransactionHandlerImpl implements TransactionHandler {

    private static final String FAIL_REASON = "Transaction failed.";

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public TransactionAggregation searchTransaction(String userName) {

        Map<Long, List<TransactionUnit>> IdMap = transactionDao.searchTransactionsByName(userName).
                stream().
                collect(groupingBy(TransactionUnit::getTransactionId));

        List<BasicTransactionInfo> basicTransactionInfos =
                IdMap.values()
                        .stream()
                        .map(TransactionHandlerImpl::generateBasicTransactionInfo)
                        .collect(toList());

        TransactionAggregation aggregation = new TransactionAggregation();
        aggregation.setTransactions(basicTransactionInfos);

        return aggregation;
    }

    @Override
    public TransactionCreationResult createTransaction(CreateTransactionRequest createTransactionRequest) {

        TransactionCreationResult transactionCreationResult = new TransactionCreationResult();

        try {
            creationHelper(createTransactionRequest);
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

        TransactionDeletionResult transactionDeletionResult = new TransactionDeletionResult();

        try {
            deleteHelper(deleteTransactionRequest);
            transactionDeletionResult.setResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            transactionDeletionResult.setResult(false);
            transactionDeletionResult.setReason(FAIL_REASON);
        }

        return transactionDeletionResult;
    }

    @Transactional
    private void creationHelper(CreateTransactionRequest createTransactionRequest) throws Exception {

        TransactionTableUnit transactionTableUnit = new TransactionTableUnit(
                createTransactionRequest.getUserName(),
                createTransactionRequest.isRound()
        );

        int transactionResult = transactionDao.createTransactionAndGetId(transactionTableUnit);

        if (transactionResult != 1 || transactionTableUnit.getTransactionId() == 0) {
            throw new Exception("Failed to create basic transaction.");
        }

        List<SegmentInsertionUnit> segments = new ArrayList<>();
        int segmentNumber = 0;

        for (int i = 0; i < createTransactionRequest.getGoSegments().size(); i++) {
            segments.add(generateSegmentUnit(transactionTableUnit,
                    createTransactionRequest.getGoSegments().get(i),
                    segmentNumber));
            segmentNumber++;
        }

        if (createTransactionRequest.isRound()) {
            for (int i = 0; i < createTransactionRequest.getBackSegments().size(); i++) {
                segments.add(generateSegmentUnit(transactionTableUnit,
                        createTransactionRequest.getBackSegments().get(i),
                        segmentNumber));
                segmentNumber++;
            }
        }

        transactionDao.createDetailedTransactions(segments);

        //need to update in memory data here
    }

    @Transactional
    private void deleteHelper(DeleteTransactionRequest deleteTransactionRequest) throws Exception {

        List<SegmentStationInfo> stationPairs =
                transactionDao.searchTransactionStations(deleteTransactionRequest.getTransactionId());

        transactionDao.deleteTransaction(deleteTransactionRequest.getTransactionId());

        //need to update in memory data here
    }

    private SegmentInsertionUnit generateSegmentUnit(TransactionTableUnit transactionTableUnit,
                                                            InputSegmentInfo inputSegmentInfo,
                                                            int segmentNumber) {

        Calendar dayCalendar = Calendar.getInstance();
        dayCalendar.set(Calendar.YEAR, inputSegmentInfo.getYear());
        dayCalendar.set(Calendar.MONTH, inputSegmentInfo.getMonth());
        dayCalendar.set(Calendar.DAY_OF_MONTH, inputSegmentInfo.getDay());
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.HOUR_OF_DAY, inputSegmentInfo.getStartHour());
        startCalendar.set(Calendar.MINUTE, inputSegmentInfo.getStartMinute());
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.HOUR_OF_DAY, inputSegmentInfo.getEndHour());
        endCalendar.set(Calendar.MINUTE, inputSegmentInfo.getEndMinute());
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);

        return new SegmentInsertionUnit(
                transactionTableUnit.getTransactionId(),
                inputSegmentInfo.getTrainName(),
                inputSegmentInfo.isFast(),
                new Date(dayCalendar.getTimeInMillis()),
                new Time(startCalendar.getTimeInMillis()),
                new Time(endCalendar.getTimeInMillis()),
                inputSegmentInfo.getPrice(),
                inputSegmentInfo.getStartStation(),
                inputSegmentInfo.getEndStation(),
                segmentNumber
        );
    }

    private static BasicTransactionInfo generateBasicTransactionInfo(List<TransactionUnit> transactionUnits) {

        BasicTransactionInfo basicTransactionInfo = new BasicTransactionInfo();
        basicTransactionInfo.setRound(transactionUnits.get(0).isRound());
        basicTransactionInfo.setUserName(transactionUnits.get(0).getUserName());
        basicTransactionInfo.setTransactionId(transactionUnits.get(0).getTransactionId());

        basicTransactionInfo.setGoSegments(new ArrayList<>());
        basicTransactionInfo.setBackSegments(new ArrayList<>());

        for (int i = 0; i < transactionUnits.size(); i++) {
            OutputSegmentInfo outputSegmentInfo = new OutputSegmentInfo(
                    transactionUnits.get(i).getTrainName(),
                    transactionUnits.get(i).isFast(),
                    transactionUnits.get(i).getDay(),
                    transactionUnits.get(i).getStartTime(),
                    transactionUnits.get(i).getEndTime(),
                    transactionUnits.get(i).getStartStation(),
                    transactionUnits.get(i).getEndStation(),
                    transactionUnits.get(i).getPrice()
            );

            if (transactionUnits.get(i).isGo()) {
                basicTransactionInfo.getGoSegments().add(outputSegmentInfo);
            } else {
                basicTransactionInfo.getBackSegments().add(outputSegmentInfo);
            }
        }

        return basicTransactionInfo;
    }
}