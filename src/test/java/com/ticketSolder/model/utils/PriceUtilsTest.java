package com.ticketSolder.model.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/11/23.
 */
public class PriceUtilsTest {

    @Test
    public void test() {
        System.out.println(PriceUtils.getPrice(false, 'A', 'G'));
    }
}