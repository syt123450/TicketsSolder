package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.report.*;
import com.ticketSolder.model.domain.mysql.SearchLogInfo;
import com.ticketSolder.model.domain.mysql.TrainDayReport;
import com.ticketSolder.model.domain.mysql.TrainTotalReport;
import com.ticketSolder.model.dao.mysql.ReportDao;
import com.ticketSolder.model.service.rest.ReportHandler;
import com.ticketSolder.model.utils.NumberFormatter;
import com.ticketSolder.model.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    @Override
    public RateResult getPerTrainRate(PerTrainRequestBean perTrainRequestBean) {

        Date date = TimeUtils.getSQLDate(
                perTrainRequestBean.getYear(),
                perTrainRequestBean.getMonth(),
                perTrainRequestBean.getDay());

        double rate = reportDao.getPerTrainReservationRate(
                perTrainRequestBean.getTrainName(),
                date
                );

        RateResult rateResult = new RateResult();
        rateResult.setRate(NumberFormatter.formatRate(rate));

        return rateResult;
    }

    @Override
    public RateResult getWholeTrainRate(WholeTrainRequestBean wholeTrainRequestBean) {

        Date date = TimeUtils.getSQLDate(
                wholeTrainRequestBean.getYear(),
                wholeTrainRequestBean.getMonth(),
                wholeTrainRequestBean.getDay()
        );

        double rate = reportDao.getSystemReservationRate(date);

        RateResult rateResult = new RateResult();
        rateResult.setRate(NumberFormatter.formatRate(rate));

        return rateResult;
    }

    @Override
    public SystemSearchState getSystemSearchState(SearchStateRequestBean searchStateRequestBean) {

        Date date = TimeUtils.getSQLDate(
                searchStateRequestBean.getYear(),
                searchStateRequestBean.getMonth(),
                searchStateRequestBean.getDay()
        );

        SearchLogInfo searchLogInfo = reportDao.getSearchState(date);

        SystemSearchState systemSearchState = new SystemSearchState(
                searchLogInfo.getSearchTimes(),
                searchLogInfo.getConnection0(),
                searchLogInfo.getConnection1(),
                searchLogInfo.getConnection2(),
                NumberFormatter.formatPercentage(searchLogInfo.getConnection0() / searchLogInfo.getSearchTimes()),
                NumberFormatter.formatPercentage(searchLogInfo.getConnection1() / searchLogInfo.getSearchTimes()),
                NumberFormatter.formatPercentage(searchLogInfo.getConnection2() / searchLogInfo.getSearchTimes()),
                NumberFormatter.formatQPS(searchLogInfo.getAverage0()),
                NumberFormatter.formatQPS(searchLogInfo.getAverage1()),
                NumberFormatter.formatQPS(searchLogInfo.getAverage2())
        );

        return systemSearchState;
    }
}
