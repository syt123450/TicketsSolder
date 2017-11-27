package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.reset.ResetResult;
import com.ticketSolder.model.service.rest.ResetHandler;
import com.ticketSolder.model.service.rest.helper.ResetHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/20.
 */

@Service
public class ResetHandlerImpl implements ResetHandler {

    @Autowired
    private ResetHelper resetHelper;

    @Override
    public ResetResult reset(int initNumber) {

        ResetResult resetResult = new ResetResult();

        try {
            resetHelper.deleteAndInit(initNumber);
             resetResult.setResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            resetResult.setResult(false);
        }

        return resetResult;
    }
}
