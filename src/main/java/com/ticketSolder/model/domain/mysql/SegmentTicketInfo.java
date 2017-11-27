package com.ticketSolder.model.domain.mysql;

import lombok.Data;

import java.sql.Date;

/**
 * Created by ss on 2017/11/17.
 */

@Data
public class SegmentTicketInfo {

    private String trainName;
    private Date day;
    private char startStation;
    private char endStation;
}