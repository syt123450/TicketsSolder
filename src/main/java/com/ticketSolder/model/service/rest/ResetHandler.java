package com.ticketSolder.model.service.rest;

import com.ticketSolder.model.bean.reset.ResetResult;

/**
 * Created by ss on 2017/11/20.
 */
public interface ResetHandler {

    ResetResult reset(int initNumber);
}
