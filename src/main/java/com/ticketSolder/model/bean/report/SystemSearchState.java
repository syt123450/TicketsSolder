package com.ticketSolder.model.bean.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/12/20.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemSearchState {

    private int totalRequest;

    private int noneRequest;
    private int oneRequest;
    private int anyRequest;

    private String nonePercentage;
    private String onePercentage;
    private String anyPercentage;

    private String noneAverage;
    private String oneAverage;
    private String anyAverage;
}
