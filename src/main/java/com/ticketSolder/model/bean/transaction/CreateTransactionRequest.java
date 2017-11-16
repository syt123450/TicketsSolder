package com.ticketSolder.model.bean.transaction;

import com.ticketSolder.model.bean.trip.TripInfo;
import lombok.Data;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class CreateTransactionRequest {

    private String userName;
    private String password;

    private boolean round;

    private TripInfo goTripInfo;
    private TripInfo backTripInfo;
}