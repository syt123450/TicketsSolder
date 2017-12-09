package com.ticketSolder.model.dao.h2;

import java.util.List;

/**
 * Created by ss on 2017/11/18.
 */
public interface TestDao {

    void insert();

    String select();

    List<String> testInit();
}
