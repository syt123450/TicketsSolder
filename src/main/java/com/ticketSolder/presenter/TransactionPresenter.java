package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketSolder.model.service.TransactionService;
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
    private TransactionService transactionService;
    private Gson gson = new GsonBuilder().create();
    private Logger logger = Logger.getLogger(TransactionPresenter.class);

    @RequestMapping("/check/{userName}")
    private String checkTransaction(@PathVariable(value = "userName", required = true) String userName) {

        logger.info("Check transaction for specific user.");

        return "";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    private String createTransaction(@RequestBody String body) {

        logger.info("Create new transaction.");

        return "";
    }

    @RequestMapping("/delete/{transactionId}")
    private String deleteTransaction(@PathVariable(value = "userName", required = true) String transactionId) {

        logger.info("Delete transaction.");

        return "";
    }
}
