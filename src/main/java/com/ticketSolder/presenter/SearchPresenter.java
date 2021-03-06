package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketSolder.model.bean.trip.TripRequest;
import com.ticketSolder.model.service.rest.SearchHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;

/**
 * Created by ss on 2017/11/16.
 */

@EnableAutoConfiguration
@RestController
//@CrossOrigin
@RequestMapping("/api/search")
public class SearchPresenter {

    @Autowired
    private SearchHandler searchHandler;
    private Gson gson = new GsonBuilder().create();
    private Logger logger = Logger.getLogger(SearchPresenter.class);

    @RequestMapping(value = "/Trip", method = RequestMethod.POST)
    public String searchTrip(@RequestBody String body) {

        logger.info("Search for trips.");

        TripRequest tripRequest = gson.fromJson(body, TripRequest.class);

        logger.info(tripRequest);

        return gson.toJson(searchHandler.searchTripInfo(tripRequest));
    }
}