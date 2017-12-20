package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */
public interface TransactionDao {

    List<TransactionUnit> searchTransactionsByName(@Param("userName") String userName);

    List<SegmentTicketInfo> searchTransactionTicketsInfo(@Param("transactionId") long transactionId);

    int cancelTransaction(@Param("transactionId") long transactionId);

    int deleteAllTransactions();

    int createTransactionAndGetId(TransactionTableUnit transactionTableUnit);

    int createDetailedTransactions(@Param("segmentInsertionUnits") List<SegmentInsertionUnit> segmentInsertionUnits);
}