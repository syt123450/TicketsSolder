package com.ticketSolder.model.utils;

import com.ticketSolder.model.service.rest.helper.SearchHelper;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by ss on 2017/11/24.
 */
public class GeneratorUtilsTest {

    @Test
    @Ignore
    public void testGenerateStations() {
        SearchHelper searchHelper = new SearchHelper();
        System.out.println(GeneratorUtils.generateStations('C', 'A'));
    }

    @Test
    @Ignore
    public void testGenerateSegments() {
        SearchHelper searchHelper = new SearchHelper();
        System.out.println(GeneratorUtils.generateSegments('A', 'C'));
    }
}