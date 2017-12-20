package com.ticketSolder.model.utils;

/**
 * Created by ss on 2017/12/20.
 */
public class NumberFormatter {

    public static String formatRate(double rate) {
        return String.format("%.2f" , 100 * rate) + "%";
    }

    public static String formatPercentage(int number) {

        return number + "%";
    }

    public static String formatQPS(int number) {

        return number + " Millisecond/Request";
    }
}
