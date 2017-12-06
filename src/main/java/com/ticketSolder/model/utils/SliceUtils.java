package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.trip.SlicedSegment;

import java.util.*;

/**
 * Created by ss on 2017/11/29.
 */
public class SliceUtils {

    private static final List<Character> fastStations = new ArrayList<Character>() {{
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
        char fastEnd;

        if (fastStations.contains(startStation)) {
            fastStart = startStation;
        } else {
            fastStart = getNearestLastFastStation(startStation);
            SlicedSegment slicedSegment = new SlicedSegment(startStation, fastStart, false);
            slicedSegments.add(slicedSegment);
        }

        if (fastStations.contains(endStation)) {
            SlicedSegment slicedSegment = new SlicedSegment(fastStart, endStation, true);
            slicedSegments.add(slicedSegment);
        } else {
            fastEnd = getNearestPreviousFastStation(endStation);

            if (fastEnd == fastStart) {

                slicedSegments.remove(0);
                SlicedSegment slicedSegment = new SlicedSegment(startStation, endStation, false);
                slicedSegments.add(slicedSegment);

                return slicedSegments;
            }

            SlicedSegment slicedSegmentFast = new SlicedSegment(fastStart, fastEnd, true);
            slicedSegments.add(slicedSegmentFast);
            SlicedSegment slicedSegmentSlow = new SlicedSegment(fastEnd, endStation, false);
            slicedSegments.add(slicedSegmentSlow);
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

        for (int i = 0; i < fastStations.size(); i++) {
            if (fastStations.get(i) - station > 0) {
                return fastStations.get(i);
            }
        }

        return 'Z';
    }

    private static char getNearestPreviousFastStation(char station) {

        for (int i = fastStations.size() - 1; i >= 0; i--) {
            if (fastStations.get(i) - station < 0) {
                return fastStations.get(i);
            }
        }

        return 'A';
    }
}