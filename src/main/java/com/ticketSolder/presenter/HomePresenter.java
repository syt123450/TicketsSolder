package com.ticketSolder.presenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePresenter {
    @RequestMapping(value = {"/", "/signup", "/signin", "/signout", "/search", "/admin", "/transaction", })
    public String index() {
        return "index.html";
    }
}
