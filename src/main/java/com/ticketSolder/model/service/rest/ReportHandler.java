package com.ticketSolder.model.service.rest;

import com.ticketSolder.model.bean.report.TrainDayReport;
import com.ticketSolder.model.bean.report.TrainTotalReport;

import java.util.List;

/**
 * Created by ss on 2017/11/29.
 */
public interface ReportHandler {

    List<TrainDayReport> getTrainReport(String trainName);

    List<TrainTotalReport> getSystemReport();

}
