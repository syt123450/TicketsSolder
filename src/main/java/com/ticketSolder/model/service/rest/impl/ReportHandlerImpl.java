package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.report.TrainDayReport;
import com.ticketSolder.model.bean.report.TrainTotalReport;
import com.ticketSolder.model.dao.mysql.ReportDao;
import com.ticketSolder.model.service.rest.ReportHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ss on 2017/11/29.
 */

@Service
@Profile("dev")
public class ReportHandlerImpl implements ReportHandler {

    @Autowired
    private ReportDao reportDao;

    @Override
    public List<TrainDayReport> getTrainReport(String trainName) {
        return reportDao.getTrainReports(trainName);
    }

    @Override
    public List<TrainTotalReport> getSystemReport() {
        return reportDao.getTrainTotalReports();
    }
}
