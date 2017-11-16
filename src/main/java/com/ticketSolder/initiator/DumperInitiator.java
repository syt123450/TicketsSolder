package com.ticketSolder.initiator;

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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        dataDumper.initDumper();
    }
}
