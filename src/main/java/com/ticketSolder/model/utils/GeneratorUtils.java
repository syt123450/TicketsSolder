package com.ticketSolder.model.utils;

import com.ticketSolder.model.bean.cancel.CanceledTransactionInfo;
import com.ticketSolder.model.bean.transaction.BasicTransactionInfo;
import com.ticketSolder.model.bean.transaction.CreateTransactionRequest;
import com.ticketSolder.model.bean.transaction.TransactionOutputSegmentInfo;
import com.ticketSolder.model.bean.trip.InputSegmentInfo;
import com.ticketSolder.model.domain.mysql.SegmentInsertionUnit;
import com.ticketSolder.model.domain.mysql.TransactionTableUnit;
import com.ticketSolder.model.domain.mysql.TransactionUnit;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ss on 2017/11/17.
 */
public class GeneratorUtils {

    private static final String DATE_FORMAT = "yyyy-MM-DD";
    private static final String TIME_FORMAT = "HH:mm";

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
                new Date(dayCalendar.getTimeInMillis()),
                new Time(startCalendar.getTimeInMillis()),
                new Time(endCalendar.getTimeInMillis()),
                inputSegmentInfo.getPrice(),
                inputSegmentInfo.getStartStation(),
                inputSegmentInfo.getEndStation(),
                segmentNumber,
                inputSegmentInfo.isFast(),
                inputSegmentInfo.getStartStation() - inputSegmentInfo.getEndStation() < 0
        );
    }

    public static BasicTransactionInfo generateBasicTransactionInfo(List<TransactionUnit> transactionUnits) {

        //init basicTransaction unit

        BasicTransactionInfo basicTransactionInfo = new BasicTransactionInfo(
                transactionUnits.get(0).getUserName(),
                transactionUnits.get(0).getTransactionId(),
                transactionUnits.get(0).isCanceled(),
                transactionUnits.get(0).isRound()
        );

        //init direction

        boolean lastDirection = transactionUnits.get(0).isGo();

        //prepare for formatter

        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        SimpleDateFormat timeFormatter = new SimpleDateFormat(TIME_FORMAT);

        //add detailed information

        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        for (TransactionUnit transactionUnit : transactionUnits) {

            startCalendar.setTime(transactionUnit.getDay());
            startCalendar.setTime(transactionUnit.getStartTime());

            endCalendar = TimeUtils.getEndCalendar(
                    transactionUnit.getDay(),
                    transactionUnit.getEndTime(),
                    startCalendar,
                    endCalendar
            );

            TransactionOutputSegmentInfo outputSegmentInfo = new TransactionOutputSegmentInfo(
                    transactionUnit.getTrainName(),
                    transactionUnit.isFast(),
                    dateFormatter.format(startCalendar),
                    timeFormatter.format(startCalendar),
                    dateFormatter.format(endCalendar),
                    timeFormatter.format(endCalendar),
                    transactionUnit.getStartStation(),
                    transactionUnit.getEndStation(),
                    transactionUnit.getPrice()
            );

            //judge whether have back in this transaction, if exist, add to back list

            if (transactionUnit.isGo() == lastDirection) {
                basicTransactionInfo.getGoSegments().add(outputSegmentInfo);
            } else {
                basicTransactionInfo.getBackSegments().add(outputSegmentInfo);
            }

            //update the direction

            lastDirection = transactionUnit.isGo();
        }


        return basicTransactionInfo;
    }

    public static List<String> generateSegments(char startStation, char endStation) {


        List<String> segments = new ArrayList<>();

        if (startStation > endStation) {
            char temp = startStation;
            startStation = endStation;
            endStation = temp;
        }

        for (char i = startStation; i < endStation; i++) {
            segments.add("" + i + (char) (i + 1));
        }

        return segments;
    }

    public static List<Long> generateTransactionIds(List<CanceledTransactionInfo> canceledTransactionInfoList) {

        List<Long> transactionIds = new ArrayList<>();

        for (CanceledTransactionInfo canceledTransactionInfo: canceledTransactionInfoList) {
            transactionIds.add(canceledTransactionInfo.getTransactionId());
        }

        return transactionIds;
    }

    public static List<CreateTransactionRequest> generateCreateTransactionRequestList(
            List<CanceledTransactionInfo> canceledTransactionInfoList) {

        return null;
    }
}