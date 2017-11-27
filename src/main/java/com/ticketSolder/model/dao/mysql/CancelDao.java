package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.bean.cancel.CanceledTransactionInfo;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by ss on 2017/11/27.
 */
public interface CancelDao {

    Time getStartTime(String trainName);

    void cancelTrain(String trainName, Date date);

    List<CanceledTransactionInfo> findTransactions(String trainName, Date date);

    void cancelTickets(List<Long> transactionIds);
}