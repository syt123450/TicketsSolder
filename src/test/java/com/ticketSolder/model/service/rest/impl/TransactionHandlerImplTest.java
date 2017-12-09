package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.transaction.DeleteTransactionRequest;
import com.ticketSolder.model.service.rest.TransactionHandler;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        deleteTransactionRequest.setTransactionId(16);
        System.out.println(transactionHandler.deleteTransaction(deleteTransactionRequest));
    }

    @Test
    @Ignore
    public void testCreateTransaction() {

    }
}