package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketSolder.model.bean.transaction.CreateTransactionRequest;
import com.ticketSolder.model.bean.transaction.DeleteTransactionRequest;
import com.ticketSolder.model.service.rest.TransactionHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ss on 2017/11/16.
 */

@EnableAutoConfiguration
@RestController
@RequestMapping("/api/transaction")
class TransactionPresenter {

    @Autowired
    private TransactionHandler transactionHandler;
    private Gson gson = new GsonBuilder().create();
    private Logger logger = Logger.getLogger(TransactionPresenter.class);

    @RequestMapping("/check/{userName}/{password}")
    private String checkTransaction(@PathVariable(value = "userName") String userName,
                                    @PathVariable(value = "password") String password) {

        logger.info("Check transaction for specific user.");

        return gson.toJson(transactionHandler.searchTransaction(userName, password));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    private String createTransaction(@RequestBody String body) {

        logger.info("Create new transaction.");

        CreateTransactionRequest createTransactionRequest = gson.fromJson(body, CreateTransactionRequest.class);

        return gson.toJson(transactionHandler.createTransaction(createTransactionRequest));
    }

    @RequestMapping("/delete/{userName}/{password}/{transactionId}")
    private String deleteTransaction(@PathVariable(value = "userName") String userName,
                                     @PathVariable(value = "password") String password,
                                     @PathVariable(value = "transactionId") long transactionId) {

        logger.info("Delete transaction.");

        DeleteTransactionRequest deleteTransactionRequest =
                new DeleteTransactionRequest(userName, password, transactionId);

        return gson.toJson(transactionHandler.deleteTransaction(deleteTransactionRequest));
    }
}
