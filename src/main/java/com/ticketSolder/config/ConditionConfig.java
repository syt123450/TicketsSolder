package com.ticketSolder.config;

import com.ticketSolder.model.service.rest.DemoHandler;
import com.ticketSolder.model.service.rest.TransactionHandler;
import com.ticketSolder.model.service.rest.impl.DemoHandlerImpl;
import com.ticketSolder.model.service.rest.impl.SearchHandlerImpl;
import com.ticketSolder.model.service.rest.mock.MockDemoHandlerImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Bean;

@Configuration
public class ConditionConfig {

    @Bean
    @Profile("dev")
    public DemoHandler devDemoHandler(){
        return new DemoHandlerImpl();
    }

    @Bean
    @Profile("mock")
    public DemoHandler mockDemoHandler(){
        return new MockDemoHandlerImpl();
    }
}
