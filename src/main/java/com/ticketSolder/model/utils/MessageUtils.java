package com.ticketSolder.model.utils;

import java.util.List;

/**
 * Created by ss on 2017/12/19.
 */
public class MessageUtils {

    private static final String SUCCESS_MESSAGE = "Hello %s,  \n\nYou successfully book the ticket %s, \nThe price is %d USD.";
    private static final String FAIL_MESSAGE = "Hello %s, \n\nYou successfully cancel the ticket %s.";
    private static final String TICKET_SEGMENT_INFO = "from %c to %c";

    public static String getSuccessMessage(List<List<Character>> segments, int price, String name) {

        return String.format(SUCCESS_MESSAGE, name, generateSegmentInfo(segments), price);
    }

    private static String generateSegmentInfo(List<List<Character>> segments) {

        String result = "";

        for (int i = 0; i < segments.size() - 1; i++) {
            result += String.format(TICKET_SEGMENT_INFO, segments.get(i).get(0), segments.get(i).get(1));
            result += " | ";
        }

        result += String.format(TICKET_SEGMENT_INFO,
                segments.get(segments.size() - 1).get(0),
                segments.get(segments.size() - 1).get(1));

        return result;
    }
}