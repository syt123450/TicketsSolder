package com.ticketSolder.model.domain.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;

/**
 * Created by ss on 2017/11/22.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultUnit {

    private String trainName;
    private boolean fast;
    private Time startTime;
    private Time endTime;
    private int ticketsLeft;
}