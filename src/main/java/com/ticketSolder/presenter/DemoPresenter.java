package com.ticketSolder.presenter;

import com.ticketSolder.model.service.rest.DemoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@RequestMapping("/api/demo")
public class DemoPresenter {

    @Autowired
    private DemoHandler demoHandler;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void getHelloMessage() {
        demoHandler.sayHello();
    }

}
