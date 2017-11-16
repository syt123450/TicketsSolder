package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketSolder.model.bean.TripRequest;
import com.ticketSolder.model.service.SearchService;
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
class SearchPresenter {

    @Autowired
    private SearchService searchService;
    private Gson gson = new GsonBuilder().create();
    private Logger logger = Logger.getLogger(SearchPresenter.class);

    @RequestMapping(value = "/Trip", method = RequestMethod.POST)
    private String searchTrip(@RequestBody String body) {

        logger.info("Search for trips.");

        TripRequest tripRequest = gson.fromJson(body, TripRequest.class);

        return gson.toJson(searchService.searchTripInfo(tripRequest));
    }
}