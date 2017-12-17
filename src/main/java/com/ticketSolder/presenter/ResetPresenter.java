package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketSolder.model.service.rest.ResetHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ss on 2017/11/20.
 */

@CrossOrigin
@EnableAutoConfiguration
@RestController
@RequestMapping("/api")
class ResetPresenter {

    private Gson gson = new GsonBuilder().create();
    private Logger logger = Logger.getLogger(SearchPresenter.class);

    @Autowired
    private ResetHandler resetHandler;

    @RequestMapping("/reset/{initNumber}")
    private String searchTrip(@PathVariable(value = "initNumber") int initNumber) {

        logger.info("Reset system.");

        return gson.toJson(resetHandler.reset(initNumber));
    }
}