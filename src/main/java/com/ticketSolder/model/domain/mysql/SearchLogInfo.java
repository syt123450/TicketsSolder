package com.ticketSolder.model.domain.mysql;

import lombok.Data;

import java.sql.Date;

/**
 * Created by ss on 2017/12/20.
 */

@Data
public class SearchLogInfo {

    private Date date;
    private int searchTimes;
    private int connection0;
    private int connection1;
    private int connection2;
    private int average0;
    private int average1;
    private int average2;
}
