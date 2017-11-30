package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.trip.SlicedSegment;

import java.util.*;

/**
 * Created by ss on 2017/11/29.
 */
public class SliceUtils {

    private static final Set<Character> fastStations = new HashSet<Character>() {{
        add('A'); add('F'); add('K'); add('P'); add('U'); add('Z');
    }};

    public static List<SlicedSegment> sliceTrip(char startStation, char endStation) {

        List<SlicedSegment> slicedSegments = new ArrayList<>();

        if (Math.abs(startStation - endStation) < 5 ||
                (Math.abs(startStation - endStation) == 5 && !fastStations.contains(startStation))) {
            SlicedSegment slicedSegment = new SlicedSegment(startStation, endStation, false);
            slicedSegments.add(slicedSegment);

            return slicedSegments;
        }

        boolean direction = startStation - endStation < 0;

        if (!direction) {
            char temp = startStation;
            startStation = endStation;
            endStation = temp;
        }

        char fastStart;

        if (fastStations.contains(startStation)) {
            fastStart = startStation;
        } else {
            fastStart = getNearestLastFastStation(startStation);
            SlicedSegment slicedSegment = new SlicedSegment(startStation, fastStart, false);
            slicedSegments.add(slicedSegment);
        }

        if (fastStations.contains(endStation)) {

        } else {

        }

        if (!direction) {
            Collections.reverse(slicedSegments);
            for (SlicedSegment slicedSegment : slicedSegments) {
                swapStartAndEnd(slicedSegment);
            }
        }

        return slicedSegments;
    }

    private static void swapStartAndEnd(SlicedSegment slicedSegment) {

        char tempStation = slicedSegment.getStartStation();
        slicedSegment.setStartStation(slicedSegment.getEndStation());
        slicedSegment.setEndStation(tempStation);
    }

    private static char getNearestLastFastStation(char station) {

        return 'A';
    }
}