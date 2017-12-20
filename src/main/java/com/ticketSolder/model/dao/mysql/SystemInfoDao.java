package com.ticketSolder.model.dao.mysql;

import org.apache.ibatis.annotations.Param;

/**
 * Created by ss on 2017/12/20.
 */
public interface SystemInfoDao {

    void initSystemInfo(@Param("totalTicketPerLine") int totalTicketPerLine);
}
