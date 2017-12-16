package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.cancel.CancelRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/7.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CancelHandlerImplTest {

    @Autowired
    private CancelHandlerImpl cancelHandler;

    @Test
    public void testCancel() {
        CancelRequest cancelRequest = new CancelRequest(
                "SB0615",
                2017,
                12,
                10
        );
        System.out.println(cancelHandler.cancel(cancelRequest));
    }
}