package com.ticketSolder.model.service.rest.helper;

import com.ticketSolder.model.dao.mysql.TicketsDao;
import com.ticketSolder.model.domain.mysql.TicketsLine;
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

    @Transactional
    public void deleteAndInit(int initNumber) {

        ticketsDao.clearTicketsHistory();
        ticketsDao.insertNewTickets(generateTicketsLine(initNumber));
    }

    private List<TicketsLine> generateTicketsLine(int initNumber) {

        List<TicketsLine> ticketsLines = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 30; i++) {

            for (int j = 1; j <= 122; j++) {

                TicketsLine ticketsLine = new TicketsLine(j, new Date(calendar.getTimeInMillis()), initNumber);
                ticketsLines.add(ticketsLine);
            }

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return ticketsLines;
    }
}
