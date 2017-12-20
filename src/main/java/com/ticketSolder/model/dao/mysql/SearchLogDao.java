package com.ticketSolder.model.dao.mysql;

import org.apache.ibatis.annotations.Param;

/**
 * Created by ss on 2017/12/20.
 */
public interface SearchLogDao {

    void clearSearchLog();

    void insertOrUpdateSearchLog();

    void updateConnectionLog(
            @Param("connectionColumn") String connectionColumn,
            @Param("averageColumn") String averageColumn,
            @Param("latency") long latency
    );
}