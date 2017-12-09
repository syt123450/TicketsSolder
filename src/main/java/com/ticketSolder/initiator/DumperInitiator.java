package com.ticketSolder.initiator;

import com.ticketSolder.model.dao.mysql.CancelDao;
import com.ticketSolder.model.service.rest.ResetHandler;
import com.ticketSolder.model.service.virtual.DataDumper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;

/**
 * Created by ss on 2017/11/16.
 */

@Controller
public class DumperInitiator implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DataDumper dataDumper;
    @Autowired
    private ResetHandler resetHandler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        resetHandler.reset(100);
        dataDumper.initDumper();
    }
}