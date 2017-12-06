package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.CanceledTransactionInfo;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by ss on 2017/11/27.
 */
public interface CancelDao {

    Time getStartTime(@Param("trainName") String trainName,
                      @Param("direction") boolean direction,
                      @Param("fast") boolean fast);

    int cancelTrain(@Param("trainName") String trainName,
                     @Param("date") Date date);

    List<CanceledTransactionInfo> findTransactions(@Param("trainName") String trainName,
                                                   @Param("date") Date date);

    int cancelTickets(@Param("transactionIds") List<Long> transactionIds);
}