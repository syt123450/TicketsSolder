package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.SegmentInsertionUnit;
import com.ticketSolder.model.domain.mysql.SegmentStationInfo;
import com.ticketSolder.model.domain.mysql.TransactionTableUnit;
import com.ticketSolder.model.domain.mysql.TransactionUnit;
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