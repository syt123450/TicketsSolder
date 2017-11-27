package com.ticketSolder.model.service.rest;

import com.ticketSolder.model.bean.cancel.CancelRequest;
import com.ticketSolder.model.bean.cancel.CancelResult;

/**
 * Created by ss on 2017/11/24.
 */
public interface CancelHandler {

    CancelResult cancel(CancelRequest cancelRequest);
}
