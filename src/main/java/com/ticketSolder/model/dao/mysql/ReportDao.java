package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.SearchLogInfo;
import com.ticketSolder.model.domain.mysql.TrainDayReport;
import com.ticketSolder.model.domain.mysql.TrainTotalReport;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by ss on 2017/12/4.
 */
public interface ReportDao {

    List<TrainDayReport> getTrainReports(@Param("trainName") String trainName);

    List<TrainTotalReport> getTrainTotalReports();

    double getPerTrainReservationRate(@Param("trainName") String trainName,
                                      @Param("date") Date date);

    double getSystemReservationRate(@Param("date") Date date);

    SearchLogInfo getSearchState(@Param("date") Date date);
}