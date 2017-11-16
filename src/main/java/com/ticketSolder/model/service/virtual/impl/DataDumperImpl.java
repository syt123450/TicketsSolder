package com.ticketSolder.model.service.virtual.impl;

import com.ticketSolder.model.service.virtual.DataDumper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/16.
 */

@Service
public class DataDumperImpl implements DataDumper {

    private Logger logger = Logger.getLogger(DataDumperImpl.class);

    @Override
    public void initDumper() {
        logger.info("start dumper.");
    }
}