package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.cancel.CancelRequest;
import com.ticketSolder.model.bean.cancel.CancelResult;
import com.ticketSolder.model.service.rest.CancelHandler;
import com.ticketSolder.model.service.rest.helper.CancelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/24.
 */

@Service
@Profile("dev")
public class CancelHandlerImpl implements CancelHandler {

    @Autowired
    private CancelHelper cancelHelper;

    @Override
    public CancelResult cancel(CancelRequest cancelRequest) {

        CancelResult cancelResult = new CancelResult();

        try {
            cancelResult.setCanceledUsers(cancelHelper.cancel(cancelRequest));
            cancelResult.setResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            cancelResult.setResult(false);
            cancelResult.setReason(e.getMessage());
        }

        return cancelResult;
    }
}