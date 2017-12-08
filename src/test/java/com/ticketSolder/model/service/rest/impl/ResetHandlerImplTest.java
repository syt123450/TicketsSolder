package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.service.rest.ResetHandler;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/6.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResetHandlerImplTest {

    @Autowired
    private ResetHandler resetHandler;

    @Test
//    @Ignore
    public void testReset() {
        resetHandler.reset(99);
    }
}