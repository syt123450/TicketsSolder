package com.ticketSolder.model.bean.transaction;

import com.ticketSolder.model.bean.trip.InputSegmentInfo;
import com.ticketSolder.model.bean.trip.TripInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class CreateTransactionRequest {

    private String userName;
    private String password;

    //field only use for rebook
    private String email;

    private boolean round;
    private int passengers;

    private List<InputSegmentInfo> goSegments;
    private List<InputSegmentInfo> backSegments;
}