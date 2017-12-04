package com.ticketSolder.model.bean.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/12/4.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainTotalReport {

    private String trainName;
    private int leftDays;
}