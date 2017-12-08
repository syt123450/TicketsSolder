package com.ticketSolder.model.service.virtual.impl;

import com.ticketSolder.model.dao.h2.InitDao;
import com.ticketSolder.model.dao.mysql.InitHelperDao;
import com.ticketSolder.model.service.virtual.DataLoader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/16.
 */

@Service
@Profile("dev")
public class DataLoaderImpl implements DataLoader {

    private Logger logger = Logger.getLogger(DataLoaderImpl.class);

    @Autowired
    private InitDao initDao;
    @Autowired
    private InitHelperDao initHelperDao;

    @Override
    public void loadData() {
        logger.info("load data to H2.");
        initDao.initTickets(initHelperDao.extractTicketsData());
    }
}
