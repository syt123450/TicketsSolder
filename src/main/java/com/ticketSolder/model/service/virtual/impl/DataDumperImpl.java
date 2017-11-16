package com.ticketSolder.model.service.virtual.impl;

import com.ticketSolder.model.service.virtual.DataDumper;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/16.
 */

@Service
public class DataDumperImpl implements DataDumper {
    @Override
    public void initDumper() {
        System.out.println("start dumper.");
    }
}