package com.ticketSolder.model.service.rest.mock;

import com.ticketSolder.model.bean.cancel.CancelRequest;
import com.ticketSolder.model.bean.cancel.CancelResult;
import com.ticketSolder.model.bean.cancel.CanceledUser;
import com.ticketSolder.model.service.rest.CancelHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/11/29.
 */

@Service
@Profile("mock")
public class MockCancelHandler implements CancelHandler {

    @Override
    public CancelResult cancel(CancelRequest cancelRequest) {

        CancelResult cancelResult = new CancelResult();

        cancelResult.setResult(true);
        cancelResult.setReason("No reason");

        List<CanceledUser> canceledUsers = new ArrayList<>();

        CanceledUser canceledUser1 = new CanceledUser(
                "syt1",
                "syt123450@gmail.com"
        );

        CanceledUser canceledUser2 = new CanceledUser(
                "syt2",
                "syt123450@gmail.com"
        );

        canceledUsers.add(canceledUser1);
        canceledUsers.add(canceledUser2);
        cancelResult.setCanceledUsers(canceledUsers);

        return cancelResult;
    }
}