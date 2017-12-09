package com.ticketSolder.initiator;

import afu.org.checkerframework.checker.igj.qual.I;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/12/7.
 */
public class DumperInitiatorTest {

    @Test
    @Ignore
    public void test() throws Exception {

        try {

            URL url = Resources.getResource("sql/H2InitScript.sql");
            String text = Resources.toString(url, Charsets.UTF_8);
            List<String> result = new ArrayList<>();
            ScriptUtils.splitSqlScript(text, ";", result);

            result.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}