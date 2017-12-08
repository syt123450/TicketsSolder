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

    public static List<List<SlicedSegment>> sliceTrip(char startStation, char endStation) {

        List<List<SlicedSegment>> slicedSegments = new ArrayList<>();

        if (Math.abs(startStation - endStation) < 5 ||
                (Math.abs(startStation - endStation) == 5 && !fastStations.contains(startStation))) {
            List<SlicedSegment> slicedSegmentList = new ArrayList<>();
            SlicedSegment slicedSegment = new SlicedSegment(startStation, endStation, false);
            slicedSegmentList.add(slicedSegment);

            slicedSegments.add(slicedSegmentList);

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
            slicedSegments = generateLastSegments(startStation, endStation);
        } else {
            fastStart = getNearestPreviousFastStation(startStation);

            SlicedSegment BSlicedSegment = new SlicedSegment(startStation, fastStart, false);

            List<List<SlicedSegment>> leftLists1 = generateLastSegments(fastStart, endStation);
            for (List<SlicedSegment> slicedSegmentList: leftLists1) {
                List<SlicedSegment> BList = new ArrayList<>();
                BList.add(BSlicedSegment);
                BList.addAll(slicedSegmentList);

                slicedSegments.add(BList);
            }

            fastStart = getNearestLastFastStation(startStation);

            SlicedSegment GSlicedSegment = new SlicedSegment(startStation, fastStart, false);

            List<List<SlicedSegment>> leftLists2 = generateLastSegments(fastStart, endStation);
            for (List<SlicedSegment> slicedSegmentList: leftLists2) {
                List<SlicedSegment> GList = new ArrayList<>();
                GList.add(GSlicedSegment);
                GList.addAll(slicedSegmentList);

                slicedSegments.add(GList);
            }
        }

        if (!direction) {
            for (List<SlicedSegment> slicedSegmentList: slicedSegments) {
                Collections.reverse(slicedSegmentList);
                for (SlicedSegment slicedSegment : slicedSegmentList) {
                    swapStartAndEnd(slicedSegment);
                }
            }
        }

        return slicedSegments;
    }

    private static List<List<SlicedSegment>> generateLastSegments(char fastStart, char endStation) {

        List<List<SlicedSegment>> leftLists = new ArrayList<>();

        char fastEnd;

        if (fastStations.contains(endStation)) {
            List<SlicedSegment> slicedSegmentList = new ArrayList<>();
            SlicedSegment slicedSegment = new SlicedSegment(fastStart, endStation, true);
            slicedSegmentList.add(slicedSegment);
            leftLists.add(slicedSegmentList);
        } else {

            fastEnd = getNearestPreviousFastStation(endStation);
            if (fastStart != fastEnd) {
                List<SlicedSegment> GGList = new ArrayList<>();

                SlicedSegment GGSlicedFastSegment = new SlicedSegment(fastStart, fastEnd, true);
                SlicedSegment GGSlicedSlowSegment = new SlicedSegment(fastEnd, endStation, false);

                GGList.add(GGSlicedFastSegment);
                GGList.add(GGSlicedSlowSegment);

                leftLists.add(GGList);
            }

            List<SlicedSegment> GBList = new ArrayList<>();
            fastEnd = getNearestLastFastStation(endStation);

            SlicedSegment GBSlicedFastSegment = new SlicedSegment(fastStart, fastEnd, true);
            SlicedSegment GBSlicedSlowSegment = new SlicedSegment(fastEnd, endStation, false);

            GBList.add(GBSlicedFastSegment);
            GBList.add(GBSlicedSlowSegment);

            leftLists.add(GBList);
        }

        return leftLists;
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