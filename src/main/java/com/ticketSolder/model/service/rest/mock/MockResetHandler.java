package com.ticketSolder.model.service.rest.mock;

import com.ticketSolder.model.bean.reset.ResetResult;
import com.ticketSolder.model.service.rest.ResetHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/29.
 */

@Service
@Profile("mock")
public class MockResetHandler implements ResetHandler {

    @Override
    public ResetResult reset(int initNumber) {

        ResetResult resetResult = new ResetResult();

        resetResult.setResult(true);

        return resetResult;
    }
}
