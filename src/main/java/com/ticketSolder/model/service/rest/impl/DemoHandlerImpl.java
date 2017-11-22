package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.service.rest.DemoHandler;

public class DemoHandlerImpl implements DemoHandler{
    @Override
    public void sayHello(){
        System.out.println("DEV_Say Hello!");
    }
}
