package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.TrainDayReport;
import com.ticketSolder.model.domain.mysql.TrainTotalReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ss on 2017/12/4.
 */
public interface ReportDao {

    List<TrainDayReport> getTrainReports(@Param("trainName") String trainName);

    List<TrainTotalReport> getTrainTotalReports();
}
