package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.cancel.CancelRequest;
import com.ticketSolder.model.bean.cancel.CancelResult;
import com.ticketSolder.model.service.rest.CancelHandler;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/24.
 */

@Service
public class CancelHandlerImpl implements CancelHandler {

    @Override
    public CancelResult cancel(CancelRequest cancelRequest) {
        return null;
    }
}
