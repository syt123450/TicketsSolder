package com.ticketSolder.model.domain.mysql;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by ss on 2017/12/20.
 */

@Data
public class DepartureTime {

    private Date startDate;
    private Time startTime;
}