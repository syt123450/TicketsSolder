package com.ticketSolder.model.bean.transaction;

import com.ticketSolder.model.bean.trip.SegmentInfo;
import com.ticketSolder.model.bean.trip.TripInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/11/16.
 */

@Data
public class BasicTransactionInfo {

    private String userName;
    private long transactionId;
    private boolean round;
    private TripInfo goTripInfo;
    private TripInfo backTripInfo;
}
