package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketSolder.model.service.rest.ReportHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ss on 2017/11/29.
 */

@EnableAutoConfiguration
@RestController
@RequestMapping("/api")
class ReportPresenter {

    private Gson gson = new GsonBuilder().create();
    private Logger logger = Logger.getLogger(ReportPresenter.class);

    @Autowired
    private ReportHandler reportHandler;

    @RequestMapping("/report/train/{trainName}")
    private String getTrainReport(@PathVariable(value = "trainName") String trainName) {

        logger.info("Get train report.");

        return gson.toJson(reportHandler.getTrainReport(trainName));
    }

    @RequestMapping("/report/system")
    private String getSystemReport() {

        logger.info("Get system report.");

        return gson.toJson(reportHandler.getSystemReport());
    }
}
