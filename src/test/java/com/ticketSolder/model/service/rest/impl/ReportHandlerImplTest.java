package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.service.rest.ReportHandler;
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
public class ReportHandlerImplTest {

    @Autowired
    private ReportHandler reportHandler;

    @Test
    @Ignore
    public void testGetTrainReport() {
        reportHandler.getTrainReport("SB0600").forEach(System.out::println);
    }

    @Test
    @Ignore
    public void testGetSystemReport() {
        reportHandler.getSystemReport().forEach(System.out::println);
    }
}