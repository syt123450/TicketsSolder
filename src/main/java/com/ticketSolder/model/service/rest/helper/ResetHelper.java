package com.ticketSolder.model.service.rest.helper;

import com.ticketSolder.model.dao.mysql.*;
import com.ticketSolder.model.domain.mysql.TicketsLine;
import com.ticketSolder.model.utils.GeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ss on 2017/11/22.
 */

@Component
public class ResetHelper {

    @Autowired
    private TicketsDao ticketsDao;
    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SystemInfoDao systemInfoDao;
    @Autowired
    private SearchLogDao searchLogDao;

    @Transactional
    public void deleteAndInit(int initNumber) {

        ticketsDao.clearTicketsHistory();
        transactionDao.deleteAllTransactions();
        searchLogDao.clearSearchLog();
        userDao.deleteAllUsers();
        systemInfoDao.initSystemInfo(initNumber);
        ticketsDao.insertNewTickets(GeneratorUtils.generateTicketsLine(initNumber));
    }
}
