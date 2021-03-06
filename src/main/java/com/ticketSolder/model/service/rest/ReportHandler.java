package com.ticketSolder.model.service.rest;

import com.ticketSolder.model.bean.report.*;
import com.ticketSolder.model.domain.mysql.TrainDayReport;
import com.ticketSolder.model.domain.mysql.TrainTotalReport;

import java.util.List;

/**
 * Created by ss on 2017/11/29.
 */
public interface ReportHandler {

    List<TrainDayReport> getTrainReport(String trainName);

    List<TrainTotalReport> getSystemReport();

    RateResult getPerTrainRate(PerTrainRequestBean perTrainRequestBean);

    RateResult getWholeTrainRate(WholeTrainRequestBean wholeTrainRequestBean);

    SystemSearchState getSystemSearchState(SearchStateRequestBean searchStateRequestBean);
}
