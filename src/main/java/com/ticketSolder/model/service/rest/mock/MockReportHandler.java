package com.ticketSolder.model.service.rest.mock;

import com.ticketSolder.model.bean.report.*;
import com.ticketSolder.model.domain.mysql.TrainDayReport;
import com.ticketSolder.model.domain.mysql.TrainTotalReport;
import com.ticketSolder.model.service.rest.ReportHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/11/29.
 */

@Service
@Profile("mock")
public class MockReportHandler implements ReportHandler {
    @Override
    public List<TrainDayReport> getTrainReport(String trainName) {

        List<TrainDayReport> trainDayReports = new ArrayList<>();


        TrainDayReport trainDayReport1 = new TrainDayReport(
                "SB0600",
                "2017-11-30",
                false,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100
        );

        TrainDayReport trainDayReport2 = new TrainDayReport(
                "SB0600",
                "2017-11-31",
                false,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100,
                100
        );

        trainDayReports.add(trainDayReport1);
        trainDayReports.add(trainDayReport2);

        return trainDayReports;
    }

    @Override
    public List<TrainTotalReport> getSystemReport() {

        List<TrainTotalReport> systemReport = new ArrayList<>();

        TrainTotalReport trainTotalReport1 = new TrainTotalReport(
            "SB0600",
                30
        );

        TrainTotalReport trainTotalReport2 = new TrainTotalReport(
                "SB0615",
                29
        );

        systemReport.add(trainTotalReport1);
        systemReport.add(trainTotalReport2);

        return systemReport;
    }

    @Override
    public RateResult getPerTrainRate(PerTrainRequestBean perTrainRequestBean) {

        RateResult rateResult = new RateResult();
        rateResult.setRate("0.08%");

        return rateResult;
    }

    @Override
    public RateResult getWholeTrainRate(WholeTrainRequestBean wholeTrainRequestBean) {
        RateResult rateResult = new RateResult();
        rateResult.setRate("0.08%");

        return rateResult;
    }

    @Override
    public SystemSearchState getSystemSearchState(SearchStateRequestBean searchStateRequestBean) {

        SystemSearchState systemSearchState = new SystemSearchState(
              100,
                30,
                30,
                40,
                "30%",
                "30%",
                "40%",
                "100 Millisecond/Request",
                "200 Millisecond/Request",
                "300 Millisecond/Request"
        );

        return systemSearchState;
    }
}
