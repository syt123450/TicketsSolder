package com.ticketSolder.model.domain.mysql;

import lombok.Data;

import java.util.List;

/**
 * Created by ss on 2017/12/6.
 */

@Data
public class NewUser {

    private long userId;
    private String name;
    private List<Order> orders;
}