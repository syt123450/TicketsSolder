package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.transaction.BasicTransactionInfo;
import com.ticketSolder.model.bean.transaction.CreateTransactionRequest;
import com.ticketSolder.model.bean.transaction.DeleteTransactionRequest;
import com.ticketSolder.model.dao.TransactionDao;
import com.ticketSolder.model.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Created by ss on 2017/11/17.
 */

@Component
public class TransactionHelper {

    @Autowired
    private TransactionDao transactionDao;

    public List<BasicTransactionInfo> searchHelper(UserInfo userInfo) throws Exception {

        Map<Long, List<TransactionUnit>> IdMap = transactionDao.searchTransactionsByName(userInfo.getUserName()).
                stream().
                collect(groupingBy(TransactionUnit::getTransactionId));

        List<BasicTransactionInfo> basicTransactionInfos =
                IdMap.values()
                        .stream()
                        .map(GeneratorUtils::generateBasicTransactionInfo)
                        .collect(toList());

        return basicTransactionInfos;
    }

    @Transactional
    public void creationHelper(UserInfo userInfo,
                               CreateTransactionRequest createTransactionRequest)
            throws Exception {

        TransactionTableUnit transactionTableUnit = new TransactionTableUnit(
                userInfo.getUserName(),
                createTransactionRequest.isRound()
        );

        int transactionResult = transactionDao.createTransactionAndGetId(transactionTableUnit);

        if (transactionResult != 1 || transactionTableUnit.getTransactionId() == 0) {
            throw new Exception("Failed to create basic transaction.");
        }

        List<SegmentInsertionUnit> segments = new ArrayList<>();
        int segmentNumber = 0;

        for (int i = 0; i < createTransactionRequest.getGoSegments().size(); i++) {
            segments.add(GeneratorUtils.generateSegmentUnit(transactionTableUnit,
                    createTransactionRequest.getGoSegments().get(i),
                    segmentNumber));
            segmentNumber++;
        }

        if (createTransactionRequest.isRound()) {
            for (int i = 0; i < createTransactionRequest.getBackSegments().size(); i++) {
                segments.add(GeneratorUtils.generateSegmentUnit(transactionTableUnit,
                        createTransactionRequest.getBackSegments().get(i),
                        segmentNumber));
                segmentNumber++;
            }
        }

        transactionDao.createDetailedTransactions(segments);

        //need to update in memory data here
    }

    @Transactional
    public void deleteHelper(UserInfo userInfo,
                             DeleteTransactionRequest deleteTransactionRequest)
            throws Exception {

        List<SegmentStationInfo> stationPairs =
                transactionDao.searchTransactionStations(deleteTransactionRequest.getTransactionId());

        transactionDao.deleteTransaction(deleteTransactionRequest.getTransactionId());

        //need to update in memory data here
    }
}
