package com.ticketSolder.model.bean.cancel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/11/27.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelRequest {

    private String trainName;
    private int year;
    private int month;
    private int day;
}