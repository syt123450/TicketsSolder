package com.ticketSolder.model.utils;

/**
 * Created by ss on 2017/11/23.
 */
public class PriceUtils {

    public static int getPrice(boolean isFast, char startStation, char endStation) {

        return isFast ? 2 * (int) Math.ceil(1.0 * Math.abs(startStation - endStation) / 5)
                : (int) Math.ceil(1.0 * Math.abs(startStation - endStation) / 5);
    }
}