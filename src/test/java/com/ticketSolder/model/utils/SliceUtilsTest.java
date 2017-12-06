package com.ticketSolder.model.utils;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/6.
 */
public class SliceUtilsTest {

    @Test
    @Ignore
    public void test() {
        List<Character> fastStations = new ArrayList<Character>() {{
            add('A'); add('F'); add('K'); add('P'); add('U'); add('Z');
        }};

        System.out.println(fastStations);
        System.out.println(fastStations.contains('B'));
    }

    @Test
    @Ignore
    public void testSliceTrip() {
        SliceUtils.sliceTrip('B', 'K').forEach(System.out::println);
    }
}