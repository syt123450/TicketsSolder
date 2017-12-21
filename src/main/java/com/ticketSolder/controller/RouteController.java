package com.ticketSolder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ss on 2017/12/8.
 */

@Controller
public class RouteController {

    @RequestMapping(value = {"/", "/signup", "/signin", "/signout", "/search", "/admin", "/transaction", "/report"})
    public String index() {
        return "index.html";
    }
}
