package com.ticketSolder.model.utils;

import com.ticketSolder.model.service.rest.helper.ResetHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

/**
 * Created by ss on 2017/11/22.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResetHelperTest {

    @Autowired
    private ResetHelper resetHelper;

    @Test
    @Ignore
    public void test() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        System.out.println(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(calendar.getTime());
    }

    @Test
    @Ignore
    public void testDeleteAndInit() {
        resetHelper.deleteAndInit(1000);
    }
}