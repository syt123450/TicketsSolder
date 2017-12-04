package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.bean.report.TrainDayReport;
import com.ticketSolder.model.bean.report.TrainTotalReport;

import java.util.List;

/**
 * Created by ss on 2017/12/4.
 */
public interface ReportDao {

    List<TrainDayReport> getTrainReports(String trainName);

    List<TrainTotalReport> getTrainTotalReports();
}
