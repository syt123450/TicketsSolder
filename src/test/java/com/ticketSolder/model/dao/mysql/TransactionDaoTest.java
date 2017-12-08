package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.SegmentInsertionUnit;
import com.ticketSolder.model.domain.mysql.SegmentTicketInfo;
import com.ticketSolder.model.domain.mysql.TransactionTableUnit;
import com.ticketSolder.model.domain.mysql.TransactionUnit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/11/29.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionDaoTest {

    @Autowired
    private TransactionDao transactionDao;

    @Test
    @Ignore
    public void testSearchTransactionsByName() {
        List<TransactionUnit> transactionUnits = transactionDao.searchTransactionsByName("syt123450");
        System.out.println(transactionUnits.size());
        transactionUnits.forEach(System.out::println);
    }

    @Test
    @Ignore
    public void testSearchTransactionTicketsInfo() {
        long transactionId = 8;
        List<SegmentTicketInfo> segmentTicketInfoList = transactionDao.searchTransactionTicketsInfo(transactionId);
        segmentTicketInfoList.forEach(System.out::println);
    }

    @Test
    @Ignore
    public void testDeleteTransaction() {
        long transactionId = 8;
        transactionDao.deleteTransaction(transactionId);
    }

    @Test
    @Ignore
    public void testCreateTransactionAndGetId() {
        TransactionTableUnit transactionTableUnit = new TransactionTableUnit();
        transactionTableUnit.setUserName("syt123450");
        transactionTableUnit.setRound(true);
        transactionTableUnit.setPassengers(5);
        transactionDao.createTransactionAndGetId(transactionTableUnit);
        System.out.println(transactionTableUnit.getTransactionId());
    }

    @Test
    @Ignore
    public void testCreateDetailedTransactions() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        SegmentInsertionUnit segmentInsertionUnit1 = new SegmentInsertionUnit(
                14,
                "kkk",
                new Date(cal.getTimeInMillis()),
                new Time(cal.getTimeInMillis()),
                new Time(cal.getTimeInMillis()),
                20,
                'B',
                'C',
                2,
                true,
                true
        );

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2000);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        cal1.set(Calendar.HOUR_OF_DAY, 1);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

        SegmentInsertionUnit segmentInsertionUnit2 = new SegmentInsertionUnit(
                14,
                "ttt",
                new Date(cal1.getTimeInMillis()),
                new Time(cal1.getTimeInMillis()),
                new Time(cal1.getTimeInMillis()),
                3,
                'A',
                'B',
                1,
                false,
                true
        );

        List<SegmentInsertionUnit> segmentInsertionUnits = new ArrayList<>();
        segmentInsertionUnits.add(segmentInsertionUnit1);
        segmentInsertionUnits.add(segmentInsertionUnit2);

        transactionDao.createDetailedTransactions(segmentInsertionUnits);
    }

    @Test
    @Ignore
    public void testDeleteAllTransactions() {
        transactionDao.deleteAllTransactions();
    }
}