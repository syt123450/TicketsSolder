package com.ticketSolder.model.service.virtual.mock;

import com.ticketSolder.model.service.virtual.DataLoader;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/12/7.
 */

@Service
@Profile("mock")
public class MockDataLoader implements DataLoader {

    private Logger logger = Logger.getLogger(MockDataLoader.class);

    @Override
    public void loadData() {
        logger.info("load data to H2.");
    }
}
