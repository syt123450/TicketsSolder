package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.transaction.CreateTransactionRequest;
import com.ticketSolder.model.bean.transaction.DeleteTransactionRequest;
import com.ticketSolder.model.bean.trip.InputSegmentInfo;
import com.ticketSolder.model.service.rest.TransactionHandler;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/6.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionHandlerImplTest {

    @Autowired
    private TransactionHandler transactionHandler;

    @Test
    @Ignore
    public void testSearchTransaction() {
        System.out.println(transactionHandler.searchTransaction("syt123450", "123456"));
    }

    @Test
    @Ignore
    public void testDeleteTransaction() {
        DeleteTransactionRequest deleteTransactionRequest = new DeleteTransactionRequest();
        deleteTransactionRequest.setUserName("syt123450");
        deleteTransactionRequest.setPassword("123456");
        deleteTransactionRequest.setTransactionId(23);
        System.out.println(transactionHandler.deleteTransaction(deleteTransactionRequest));

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void testCreateTransaction() {
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setUserName("syt123450");
        createTransactionRequest.setPassword("123456");
        createTransactionRequest.setRound(false);
        createTransactionRequest.setPassengers(3);

        List<InputSegmentInfo> goSegments = new ArrayList<>();

        InputSegmentInfo inputSegmentInfo1 = new InputSegmentInfo(
            "SB0615",
                false,
                2017,
                12,
                10,
                6,
                23,
                6,
                36,
                'B',
                'D',
                2

        );

        InputSegmentInfo inputSegmentInfo2 = new InputSegmentInfo(
                "SB0630",
                false,
                2017,
                12,
                10,
                6,
                54,
                6,
                59,
                'D',
                'E',
                2
        );

        goSegments.add(inputSegmentInfo1);
        goSegments.add(inputSegmentInfo2);

        createTransactionRequest.setGoSegments(goSegments);

        System.out.println(transactionHandler.createTransaction(createTransactionRequest));
    }
}