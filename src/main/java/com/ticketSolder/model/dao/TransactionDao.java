package com.ticketSolder.model.dao;

import com.ticketSolder.model.domain.SegmentInsertionUnit;
import com.ticketSolder.model.domain.SegmentStationInfo;
import com.ticketSolder.model.domain.TransactionTableUnit;
import com.ticketSolder.model.domain.TransactionUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */
public interface TransactionDao {

    List<TransactionUnit> searchTransactionsByName(@Param("userName") String userName);

    List<SegmentStationInfo> searchTransactionStations(@Param("transactionId") long transactionId);

    int deleteTransaction(@Param("transactionId") long transactionId);

    int createTransactionAndGetId(TransactionTableUnit transactionTableUnit);

    int createDetailedTransactions(@Param("segmentInsertionUnits") List<SegmentInsertionUnit> segmentInsertionUnits);
}