package com.ticketSolder.model.utils;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

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