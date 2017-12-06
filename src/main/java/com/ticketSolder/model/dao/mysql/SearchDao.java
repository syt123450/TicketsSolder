package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.SearchResultUnit;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */
public interface SearchDao {

    List<SearchResultUnit> search(@Param("startDate") Date startDate,
                                  @Param("startTime") Time startTime,
                                  @Param("startStation") char startStation,
                                  @Param("endStation") char endStation,
                                  @Param("segments") List<String> segments,
                                  @Param("direction") boolean direction,
                                  @Param("exactly") boolean exactly,
                                  @Param("fast") boolean fast);
}