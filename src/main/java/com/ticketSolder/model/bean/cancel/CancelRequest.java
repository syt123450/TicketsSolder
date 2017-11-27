package com.ticketSolder.model.bean.cancel;

import lombok.Data;

/**
 * Created by ss on 2017/11/27.
 */

@Data
public class CancelRequest {

    private String trainName;
    private int year;
    private int month;
    private int day;
}