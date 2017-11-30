package com.ticketSolder.model.bean.cancel;

import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/11/27.
 */

@Data
public class CancelResult {

    private boolean result;
    private String reason;
    private List<CanceledUser> canceledUsers;
}
