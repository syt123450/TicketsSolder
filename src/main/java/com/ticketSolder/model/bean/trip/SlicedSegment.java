package com.ticketSolder.model.bean.trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ss on 2017/11/29.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlicedSegment {

    private char startStation;
    private char endStation;
    private boolean fast;
}