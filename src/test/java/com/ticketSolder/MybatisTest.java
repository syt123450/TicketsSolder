package com.ticketSolder;

import com.ticketSolder.model.dao.mysql.TransactionDao;
import com.ticketSolder.model.domain.mysql.SegmentInsertionUnit;
import com.ticketSolder.model.domain.mysql.TransactionTableUnit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;



/**
 * Created by ss on 2017/11/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MybatisTest {

    @Autowired
    private TransactionDao transactionDao;

    @Test
    @Ignore
    public void testCreateTransactionAndGetId() {
        TransactionTableUnit transactionTableUnit = new TransactionTableUnit();
        transactionTableUnit.setUserName("syt123450");
        transactionTableUnit.setRound(true);
        transactionDao.createTransactionAndGetId(transactionTableUnit);
        System.out.println(transactionTableUnit.getTransactionId());
    }

    @Test
    @Ignore
    public void testCreateDetailedTransactions() {

        SegmentInsertionUnit segmentInsertionUnit1 = new SegmentInsertionUnit();

        segmentInsertionUnit1.setTransactionId(4);
        segmentInsertionUnit1.setTrainName("kkk");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        segmentInsertionUnit1.setDay(new Date(cal.getTimeInMillis()));
        segmentInsertionUnit1.setStartTime(new Time(cal.getTimeInMillis()));
        segmentInsertionUnit1.setEndTime(new Time(cal.getTimeInMillis()));
        segmentInsertionUnit1.setPrice(1.0);
        segmentInsertionUnit1.setStartStation('B');
        segmentInsertionUnit1.setEndStation('C');
        segmentInsertionUnit1.setSegmentNumber(2);
        segmentInsertionUnit1.setFast(true);

        SegmentInsertionUnit segmentInsertionUnit2 = new SegmentInsertionUnit();

        segmentInsertionUnit2.setTransactionId(4);
        segmentInsertionUnit2.setTrainName("ttt");
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2000);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        cal1.set(Calendar.HOUR_OF_DAY, 1);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        segmentInsertionUnit2.setDay(new Date(cal1.getTimeInMillis()));
        segmentInsertionUnit2.setStartTime(new Time(cal1.getTimeInMillis()));
        segmentInsertionUnit2.setEndTime(new Time(cal1.getTimeInMillis()));
        segmentInsertionUnit2.setPrice(1.0);
        segmentInsertionUnit2.setStartStation('A');
        segmentInsertionUnit2.setEndStation('B');
        segmentInsertionUnit2.setSegmentNumber(1);
        segmentInsertionUnit2.setFast(false);

        List<SegmentInsertionUnit> segmentInsertionUnits = new ArrayList<>();
        segmentInsertionUnits.add(segmentInsertionUnit1);
        segmentInsertionUnits.add(segmentInsertionUnit2);

        transactionDao.createDetailedTransactions(segmentInsertionUnits);
    }

    @Test
    @Ignore
    public void testDeleteTransaction() {
        transactionDao.deleteTransaction(3);
    }

    @Test
    @Ignore
    public void testSearchTransactionsByName() {
        transactionDao.searchTransactionsByName("syt123450").forEach(System.out::println);
    }

    @Test
    @Ignore
    public void testSearchTransactionStations() {
        transactionDao.searchTransactionTicketsInfo(6).forEach(System.out::println);
    }
}
