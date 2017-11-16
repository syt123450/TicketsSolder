package com.ticketSolder.model.dao;

import com.ticketSolder.model.domain.TransactionUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */
public interface TransactionDao {

    List<TransactionUnit> searchTransactionsByName(@Param("userName") String userName);

    int deleteTransactionById(@Param("transactionId") long transactionId);

    int createTransaction();
}