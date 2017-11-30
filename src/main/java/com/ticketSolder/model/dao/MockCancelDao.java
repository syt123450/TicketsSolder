package com.ticketSolder.model.dao;

import com.ticketSolder.model.bean.cancel.CanceledTransactionInfo;
import com.ticketSolder.model.dao.mysql.CancelDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by ss on 2017/11/29.
 */

@Component
public class MockCancelDao implements CancelDao {
    @Override
    public Time getStartTime(@Param("trainName") String trainName) {
        return null;
    }

    @Override
    public void cancelTrain(@Param("trainName") String trainName, @Param("date") Date date) {

    }

    @Override
    public List<CanceledTransactionInfo> findTransactions(@Param("trainName") String trainName, @Param("date") Date date) {
        return null;
    }

    @Override
    public void cancelTickets(@Param("transactionIds") List<Long> transactionIds) {

    }
}
