package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.mysql.TicketsLine;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by ss on 2017/11/22.
 */

public interface TicketsDao {

    void clearTicketsHistory();
    void insertNewTickets(@Param("lines") List<TicketsLine> lines);
}
