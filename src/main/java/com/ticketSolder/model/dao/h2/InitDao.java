package com.ticketSolder.model.dao.h2;

import com.ticketSolder.model.domain.shared.TicketInfoUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**d
 * Created by ss on 2017/12/7.
 */
public interface InitDao {

    void initTickets(@Param("ticketInfoUnits") List<TicketInfoUnit> ticketInfoUnits);
}