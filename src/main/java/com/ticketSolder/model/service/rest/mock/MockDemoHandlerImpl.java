package com.ticketSolder.model.service.rest.mock;

import com.ticketSolder.model.service.rest.DemoHandler;

public class MockDemoHandlerImpl implements DemoHandler {
    @Override
    public void sayHello() {
        System.out.println("MOCK_Say Hello!!!");
    }
}
