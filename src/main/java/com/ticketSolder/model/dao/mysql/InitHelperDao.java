package com.ticketSolder.model.dao.mysql;

import com.ticketSolder.model.domain.shared.TicketInfoUnit;

import java.util.List;

/**
 * Created by ss on 2017/12/7.
 */
public interface InitHelperDao {

    List<TicketInfoUnit> extractTicketsData();
}