package com.ticketSolder.model.service.virtual.impl;

import com.ticketSolder.model.service.virtual.DataLoader;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/16.
 */

@Service
public class DataLoaderImpl implements DataLoader {

    private Logger logger = Logger.getLogger(DataLoaderImpl.class);

    @Override
    public void loadData() {
        logger.info("load data to memory.");
    }
}
