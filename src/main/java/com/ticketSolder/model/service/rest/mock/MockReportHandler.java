package com.ticketSolder.model.service.rest.mock;

import com.ticketSolder.model.service.rest.ReportHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/29.
 */

@Service
@Profile("mock")
public class MockReportHandler implements ReportHandler {
}
