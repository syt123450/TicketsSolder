package com.ticketSolder.model.service.virtual.impl;

import com.ticketSolder.model.service.virtual.DataLoader;
import org.springframework.stereotype.Service;

/**
 * Created by ss on 2017/11/16.
 */

@Service
public class DataLoaderImpl implements DataLoader {
    @Override
    public void loadData() {
        System.out.println("load data to memory.");
    }
}
