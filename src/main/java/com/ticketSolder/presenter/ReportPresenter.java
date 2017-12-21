package com.ticketSolder.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketSolder.model.bean.report.PerTrainRequestBean;
import com.ticketSolder.model.bean.report.SearchStateRequestBean;
import com.ticketSolder.model.bean.report.WholeTrainRequestBean;
import com.ticketSolder.model.service.rest.ReportHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/report/perTrain", method = RequestMethod.POST)
    private String getPerTrainRate(@RequestBody String body) {

        logger.info("Get per train rate.");

        PerTrainRequestBean perTrainRequestBean = gson.fromJson(body, PerTrainRequestBean.class);

        logger.info(perTrainRequestBean);

        return gson.toJson(reportHandler.getPerTrainRate(perTrainRequestBean));
    }

    @RequestMapping(value = "/report/whole", method = RequestMethod.POST)
    private String getWholeTrainRate(@RequestBody String body) {

        logger.info("Get whole day rate.");

        WholeTrainRequestBean wholeTrainRequestBean = gson.fromJson(body, WholeTrainRequestBean.class);

        logger.info(wholeTrainRequestBean);

        return gson.toJson(reportHandler.getWholeTrainRate(wholeTrainRequestBean));
    }

    @RequestMapping(value = "/report/state", method = RequestMethod.POST)
    private String getSystemSearchState(@RequestBody String body) {

        logger.info("Get system search state info.");

        SearchStateRequestBean searchStateRequestBean = gson.fromJson(body, SearchStateRequestBean.class);

        logger.info(searchStateRequestBean);

        return gson.toJson(reportHandler.getSystemSearchState(searchStateRequestBean));
    }
}
