package com.ticketSolder.model.service.rest.helper;

import com.ticketSolder.model.bean.transaction.BasicTransactionInfo;
import com.ticketSolder.model.bean.transaction.CreateTransactionRequest;
import com.ticketSolder.model.bean.transaction.DeleteTransactionRequest;
import com.ticketSolder.model.dao.mysql.TicketsDao;
import com.ticketSolder.model.dao.mysql.TransactionDao;
import com.ticketSolder.model.domain.mysql.*;
import com.ticketSolder.model.utils.GeneratorUtils;
import com.ticketSolder.model.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    private TicketsDao ticketsDao;


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

        List<SegmentInsertionUnit> segmentsInsertionInfo = new ArrayList<>();
        int segmentNumber = 0;

        //insert transaction log into database

        for (int i = 0; i < createTransactionRequest.getGoSegments().size(); i++) {
            segmentsInsertionInfo.add(GeneratorUtils.generateSegmentUnit(transactionTableUnit,
                    createTransactionRequest.getGoSegments().get(i),
                    segmentNumber));
            segmentNumber++;
        }

        if (createTransactionRequest.isRound()) {
            for (int i = 0; i < createTransactionRequest.getBackSegments().size(); i++) {
                segmentsInsertionInfo.add(GeneratorUtils.generateSegmentUnit(transactionTableUnit,
                        createTransactionRequest.getBackSegments().get(i),
                        segmentNumber));
                segmentNumber++;
            }
        }

        transactionDao.createDetailedTransactions(segmentsInsertionInfo);

        //update the tickets table

        for (int i = 0; i < createTransactionRequest.getGoSegments().size(); i++) {
            ticketsDao.purchaseTickets(
                    createTransactionRequest.getGoSegments().get(i).getTrainName(),
                    GeneratorUtils.generateSegments(
                            createTransactionRequest.getGoSegments().get(i).getStartStation(),
                            createTransactionRequest.getGoSegments().get(i).getEndStation()
                    ),
                    TimeUtils.getSQLDate(
                            createTransactionRequest.getGoSegments().get(i).getYear(),
                            createTransactionRequest.getGoSegments().get(i).getMonth(),
                            createTransactionRequest.getGoSegments().get(i).getDay()
                    )
            );
        }

        if (createTransactionRequest.isRound()) {
            for (int i = 0; i < createTransactionRequest.getBackSegments().size(); i++) {
                ticketsDao.purchaseTickets(
                        createTransactionRequest.getBackSegments().get(i).getTrainName(),
                        GeneratorUtils.generateSegments(
                                createTransactionRequest.getBackSegments().get(i).getStartStation(),
                                createTransactionRequest.getBackSegments().get(i).getEndStation()
                        ),
                        TimeUtils.getSQLDate(
                                createTransactionRequest.getBackSegments().get(i).getYear(),
                                createTransactionRequest.getBackSegments().get(i).getMonth(),
                                createTransactionRequest.getBackSegments().get(i).getDay()
                        )
                );
            }
        }
    }

    @Transactional
    public void deleteHelper(UserInfo userInfo,
                             DeleteTransactionRequest deleteTransactionRequest)
            throws Exception {

        //delete transaction log

        transactionDao.deleteTransaction(deleteTransactionRequest.getTransactionId());

        //"return" tickets to tickets table

        List<SegmentTicketInfo> ticketsInfo =
                transactionDao.searchTransactionTicketsInfo(deleteTransactionRequest.getTransactionId());

        for (SegmentTicketInfo ticketInfo: ticketsInfo) {
            ticketsDao.returnTickets(
                    ticketInfo.getTrainName(),
                    GeneratorUtils.generateSegments(
                            ticketInfo.getStartStation(),
                            ticketInfo.getEndStation()
                    ),
                    ticketInfo.getDay()
            );
        }
    }
}