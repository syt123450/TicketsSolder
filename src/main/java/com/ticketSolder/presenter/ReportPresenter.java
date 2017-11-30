package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

    @RequestMapping("/report/{trainName}")
    private String getReport() {

        logger.info("Cancel train.");



        return "";
    }
}
