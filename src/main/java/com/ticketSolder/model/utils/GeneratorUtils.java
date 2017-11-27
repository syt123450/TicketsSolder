package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.transaction.BasicTransactionInfo;
import com.ticketSolder.model.bean.transaction.TransactionOutputSegmentInfo;
import com.ticketSolder.model.bean.trip.InputSegmentInfo;
import com.ticketSolder.model.domain.mysql.SegmentInsertionUnit;
import com.ticketSolder.model.domain.mysql.TransactionTableUnit;
import com.ticketSolder.model.domain.mysql.TransactionUnit;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ss on 2017/11/17.
 */
public class GeneratorUtils {

    public static SegmentInsertionUnit generateSegmentUnit(TransactionTableUnit transactionTableUnit,
                                                     InputSegmentInfo inputSegmentInfo,
                                                     int segmentNumber) {

        Calendar dayCalendar = Calendar.getInstance();
        dayCalendar.set(Calendar.YEAR, inputSegmentInfo.getYear());
        dayCalendar.set(Calendar.MONTH, inputSegmentInfo.getMonth());
        dayCalendar.set(Calendar.DAY_OF_MONTH, inputSegmentInfo.getDay());
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.HOUR_OF_DAY, inputSegmentInfo.getStartHour());
        startCalendar.set(Calendar.MINUTE, inputSegmentInfo.getStartMinute());
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.HOUR_OF_DAY, inputSegmentInfo.getEndHour());
        endCalendar.set(Calendar.MINUTE, inputSegmentInfo.getEndMinute());
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);

        return new SegmentInsertionUnit(
                transactionTableUnit.getTransactionId(),
                inputSegmentInfo.getTrainName(),
                inputSegmentInfo.isFast(),
                new Date(dayCalendar.getTimeInMillis()),
                new Time(startCalendar.getTimeInMillis()),
                new Time(endCalendar.getTimeInMillis()),
                inputSegmentInfo.getPrice(),
                inputSegmentInfo.getStartStation(),
                inputSegmentInfo.getEndStation(),
                segmentNumber,
                inputSegmentInfo.getStartStation() - inputSegmentInfo.getEndStation() < 0
        );
    }

    public static BasicTransactionInfo generateBasicTransactionInfo(List<TransactionUnit> transactionUnits) {

        BasicTransactionInfo basicTransactionInfo = new BasicTransactionInfo();
        basicTransactionInfo.setRound(transactionUnits.get(0).isRound());
        basicTransactionInfo.setUserName(transactionUnits.get(0).getUserName());
        basicTransactionInfo.setTransactionId(transactionUnits.get(0).getTransactionId());

        basicTransactionInfo.setGoSegments(new ArrayList<>());
        basicTransactionInfo.setBackSegments(new ArrayList<>());

        for (int i = 0; i < transactionUnits.size(); i++) {
            TransactionOutputSegmentInfo outputSegmentInfo = new TransactionOutputSegmentInfo(
//                    transactionUnits.get(i).getTrainName(),
//                    transactionUnits.get(i).isFast(),
//                    transactionUnits.get(i).getDay(),
//                    transactionUnits.get(i).getStartTime(),
//                    transactionUnits.get(i).getEndTime(),
//                    transactionUnits.get(i).getStartStation(),
//                    transactionUnits.get(i).getEndStation(),
//                    transactionUnits.get(i).getPrice()
            );

            if (transactionUnits.get(i).isGo()) {
                basicTransactionInfo.getGoSegments().add(outputSegmentInfo);
            } else {
                basicTransactionInfo.getBackSegments().add(outputSegmentInfo);
            }
        }

        return basicTransactionInfo;
    }

    public static List<Character> generateStations(char startStation, char endStation) {

        List<Character> stations = new ArrayList<>();

        if (startStation > endStation) {
            char temp = startStation;
            startStation = endStation;
            endStation = temp;
        }

        for (char i = startStation; i <= endStation; i++) {
            stations.add(i);
        }

        return stations;

    }

    public static List<String> generateSegments(char startStation, char endStation) {


        List<String> segments = new ArrayList<>();

        if (startStation > endStation) {
            char temp = startStation;
            startStation = endStation;
            endStation = temp;
        }

        for (char i = startStation; i < endStation; i++) {
            segments.add("" + i + (char)(i + 1));
        }

        return segments;
    }
}