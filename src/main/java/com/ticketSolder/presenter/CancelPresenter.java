package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketSolder.model.bean.cancel.CancelRequest;
import com.ticketSolder.model.service.rest.CancelHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ss on 2017/11/24.
 */

@EnableAutoConfiguration
@RestController
@RequestMapping("/api")
class CancelPresenter {

    private Gson gson = new GsonBuilder().create();
    private Logger logger = Logger.getLogger(CancelPresenter.class);

    @Autowired
    private CancelHandler cancelHandler;

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    private String cancelTrain(@RequestBody String body) {

        logger.info("Cancel train.");

        CancelRequest cancelRequest = gson.fromJson(body, CancelRequest.class);

        return gson.toJson(cancelHandler.cancel(cancelRequest));
    }
}