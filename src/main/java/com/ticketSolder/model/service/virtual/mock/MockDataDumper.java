package com.ticketSolder.model.service.virtual.mock;

import com.ticketSolder.model.service.virtual.DataDumper;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/12/7.
 */

@Service
@Profile("mock")
public class MockDataDumper implements DataDumper {

    private Logger logger = Logger.getLogger(MockDataDumper.class);

    @Override
    public void initDumper() {
        logger.info("start dumper.");
    }
}
