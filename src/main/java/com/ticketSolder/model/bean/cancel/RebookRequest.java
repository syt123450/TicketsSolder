package com.ticketSolder.model.bean.cancel;

import com.ticketSolder.model.bean.trip.TripRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ss on 2017/11/29.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RebookRequest {

    private String userName;
    private String password;
    private int passengers;
    private TripRequest tripRequest;
}
