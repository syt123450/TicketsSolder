package com.ticketSolder.model.domain.mysql;

import lombok.Data;
import java.sql.Time;

/**
 * Created by ss on 2017/11/22.
 */

@Data
public class SearchResultUnit {

    private String trainName;
    private boolean fast;
    private Time startTime;
    private Time endTime;
    private int ticketsLeft;
}