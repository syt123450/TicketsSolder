package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;

/**
 * Created by ss on 2017/11/16.
 */

@EnableAutoConfiguration
@RestController
@RequestMapping("/api/search")
public class SearchPresenter {

    private Gson gson = new GsonBuilder().create();
    private Logger logger = Logger.getLogger(SearchPresenter.class);

    @RequestMapping(value = "/singleTrip", method = RequestMethod.POST)
    private String searchSingleTrip(@RequestBody String body) {

        logger.info("Search for single trip.");

        return "";
    }

    @RequestMapping(value = "/roundTrip", method = RequestMethod.POST)
    private String searchRoundTrip(@RequestBody String body) {

        logger.info("Search for round trip.");

        return "";
    }
}