package com.ticketSolder.model.utils;

import com.ticketSolder.model.domain.mysql.CanceledSegmentInfo;
import com.ticketSolder.model.domain.mysql.CanceledTransactionInfo;
import com.ticketSolder.model.bean.cancel.RebookRequest;
import com.ticketSolder.model.bean.transaction.BasicTransactionInfo;
import com.ticketSolder.model.bean.transaction.CreateTransactionRequest;
import com.ticketSolder.model.bean.transaction.TransactionOutputSegmentInfo;
import com.ticketSolder.model.bean.trip.*;
import com.ticketSolder.model.domain.mysql.*;
import org.apache.log4j.Logger;

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

    private static Logger logger = Logger.getLogger(GeneratorUtils.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm";

    public static SearchOutputSegmentInfo generateSearchOutputSegmentInfoFromSlice
            (SlicedSegment slicedSegment,
             SearchResultUnit searchResultUnit,
             Calendar startCalendar) {

        logger.debug("Generate search output for sliced segment.");

        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        SimpleDateFormat timeFormatter = new SimpleDateFormat(TIME_FORMAT);

        Date startDate = new Date(startCalendar.getTimeInMillis());

        Calendar trainStart;
        Calendar trainEnd = Calendar.getInstance();

        Time trainStartTime = searchResultUnit.getStartTime();
        Time trainEndTime = searchResultUnit.getEndTime();

        trainStart = TimeUtils.getCalendarFromSQLTimer(startDate, trainStartTime);
        trainEnd = TimeUtils.getEndCalendar(startDate, trainEndTime, trainStart);

        return new SearchOutputSegmentInfo(
                searchResultUnit.getTrainName(),
                searchResultUnit.isFast(),
                dateFormatter.format(trainStart.getTime()),
                timeFormatter.format(trainStart.getTime()),
                dateFormatter.format(trainEnd.getTime()),
                timeFormatter.format(trainEnd.getTime()),
                slicedSegment.getStartStation(),
                slicedSegment.getEndStation(),
                PriceUtils.getPrice(searchResultUnit.isFast(),
                        slicedSegment.getStartStation(),
                        slicedSegment.getEndStation()),
                searchResultUnit.getTicketsLeft()
        );
    }

    public static SearchOutputSegmentInfo generateSearchOutputSegmentInfo(BasicTripSearchRequest basicTripSearchRequest,
                                                           SearchResultUnit searchResultUnit,
                                                           Calendar startCalendar,
                                                           Date startDate) {

        logger.info(searchResultUnit);

        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        SimpleDateFormat timeFormatter = new SimpleDateFormat(TIME_FORMAT);

        logger.info("Start calendar for generate search output is: " + startCalendar.getTime());

        Calendar trainStart;
        Calendar trainEnd;

        Time trainStartTime = searchResultUnit.getStartTime();
        Time trainEndTime = searchResultUnit.getEndTime();

        trainStart = TimeUtils.getCalendarFromSQLTimer(startDate, trainStartTime);
        trainEnd = TimeUtils.getEndCalendar(startDate, trainEndTime, trainStart);

        return new SearchOutputSegmentInfo(
                searchResultUnit.getTrainName(),
                searchResultUnit.isFast(),
                dateFormatter.format(trainStart.getTime()),
                timeFormatter.format(trainStart.getTime()),
                dateFormatter.format(trainEnd.getTime()),
                timeFormatter.format(trainEnd.getTime()),
                basicTripSearchRequest.getStartStation(),
                basicTripSearchRequest.getEndStation(),
                PriceUtils.getPrice(searchResultUnit.isFast(),
                        basicTripSearchRequest.getStartStation(),
                        basicTripSearchRequest.getEndStation()),
                searchResultUnit.getTicketsLeft()
        );
    }

    public static SegmentInsertionUnit generateSegmentUnit(TransactionTableUnit transactionTableUnit,
                                                           InputSegmentInfo inputSegmentInfo,
                                                           int segmentNumber) {

        Calendar dayCalendar = Calendar.getInstance();
        dayCalendar.set(Calendar.YEAR, inputSegmentInfo.getYear());
        dayCalendar.set(Calendar.MONTH, inputSegmentInfo.getMonth() - 1);
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

        Calendar startCalendar;
        Calendar endCalendar;

        for (TransactionUnit transactionUnit : transactionUnits) {

            startCalendar = TimeUtils.getCalendarFromSQLTimer(transactionUnit.getDay(),
                    transactionUnit.getStartTime());

            endCalendar = TimeUtils.getEndCalendar(
                    transactionUnit.getDay(),
                    transactionUnit.getEndTime(),
                    startCalendar
            );

            TransactionOutputSegmentInfo outputSegmentInfo = new TransactionOutputSegmentInfo(
                    transactionUnit.getTrainName(),
                    transactionUnit.isFast(),
                    dateFormatter.format(startCalendar.getTime()),
                    timeFormatter.format(startCalendar.getTime()),
                    dateFormatter.format(endCalendar.getTime()),
                    timeFormatter.format(endCalendar.getTime()),
                    transactionUnit.getStartStation(),
                    transactionUnit.getEndStation(),
                    transactionUnit.getPrice(),
                    transactionUnit.getPassengers()
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

    public static List<TicketsLine> generateTicketsLine(int initNumber) {

        List<TicketsLine> ticketsLines = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 30; i++) {

            for (int j = 1; j <= 122; j++) {

                TicketsLine ticketsLine = new TicketsLine(j, new Date(calendar.getTimeInMillis()), initNumber);
                ticketsLines.add(ticketsLine);
            }

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return ticketsLines;
    }

    public static List<Long> generateTransactionIds(List<CanceledTransactionInfo> canceledTransactionInfoList) {

        List<Long> transactionIds = new ArrayList<>();

        for (CanceledTransactionInfo canceledTransactionInfo : canceledTransactionInfoList) {
            transactionIds.add(canceledTransactionInfo.getTransactionId());
        }

        return transactionIds;
    }

    public static List<RebookRequest> generateTripRequestList(
            List<CanceledTransactionInfo> canceledTransactionInfoList) {

        List<RebookRequest> rebookRequests = new ArrayList<>();

        for (CanceledTransactionInfo canceledTransactionInfo : canceledTransactionInfoList) {

            RebookRequest rebookRequest = new RebookRequest(
                    canceledTransactionInfo.getUserName(),
                    canceledTransactionInfo.getPassword(),
                    canceledTransactionInfo.getPassengers(),
                    generateTripRequestFromCancelInfo(canceledTransactionInfo)
            );

            rebookRequests.add(rebookRequest);
        }

        return rebookRequests;
    }

    public static CreateTransactionRequest generateCreationRequestFromSearch(
            TripSearchResult tripSearchResult,
            RebookRequest rebookRequest) {

        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();

        createTransactionRequest.setUserName(rebookRequest.getUserName());
        createTransactionRequest.setPassword(rebookRequest.getPassword());
        createTransactionRequest.setPassengers(rebookRequest.getPassengers());
        createTransactionRequest.setRound(tripSearchResult.isRound());

        boolean isFast = tripSearchResult.getGoTripInfoAggregation().isFast() ||
                (tripSearchResult.getBackTripInfoAggregation() != null && tripSearchResult.getBackTripInfoAggregation().isFast());

        List<InputSegmentInfo> goSegments = generateInputSegmentInfoListFromTripInfo(
                isFast ? tripSearchResult.getGoTripInfoAggregation().getFastTrainTrips().get(0) :
                        tripSearchResult.getGoTripInfoAggregation().getNormalTrainTrips().get(0)
        );
        createTransactionRequest.setGoSegments(goSegments);

        if (tripSearchResult.isRound()) {
            List<InputSegmentInfo> backSegments = generateInputSegmentInfoListFromTripInfo(
                    isFast ? tripSearchResult.getBackTripInfoAggregation().getFastTrainTrips().get(0) :
                            tripSearchResult.getBackTripInfoAggregation().getNormalTrainTrips().get(0)
            );
            createTransactionRequest.setBackSegments(backSegments);
        }

        return createTransactionRequest;
    }

    private static TripRequest generateTripRequestFromCancelInfo(
            CanceledTransactionInfo canceledTransactionInfo) {

        TripRequest tripRequest = new TripRequest();

        tripRequest.setRound(canceledTransactionInfo.isRound());

        boolean isFast = false;

        CanceledSegmentInfo firstSegment = canceledTransactionInfo.getCanceledSegmentInfoList().get(0);
        boolean direction = firstSegment.getStartStation() - firstSegment.getEndStation() < 0;
        boolean directionChanged = false;

        Calendar startCalendar = TimeUtils.getCalendarFromSQLTimer(
                firstSegment.getStartDate(),
                firstSegment.getStartTime()
        );

        tripRequest.setGoYear(startCalendar.get(Calendar.YEAR));
        tripRequest.setGoMonth(startCalendar.get(Calendar.MONTH) + 1);
        tripRequest.setGoDay(startCalendar.get(Calendar.DAY_OF_MONTH));
        tripRequest.setGoHour(startCalendar.get(Calendar.HOUR_OF_DAY));
        tripRequest.setGoMinute(startCalendar.get(Calendar.MINUTE));

        char startStation = firstSegment.getStartStation();
        char endStation = firstSegment.getEndStation();

        tripRequest.setGoStartStation(startStation);

        for (int i = 1; i < canceledTransactionInfo.getCanceledSegmentInfoList().size(); i++) {

            CanceledSegmentInfo canceledSegmentInfo = canceledTransactionInfo.getCanceledSegmentInfoList().get(i);

            if (canceledSegmentInfo.isFast()) {
                isFast = true;
            }

            boolean tempDirection =
                    canceledSegmentInfo.getStartStation() - canceledSegmentInfo.getEndStation() < 0;

            if (tempDirection != direction) {

                directionChanged = true;
                tripRequest.setGoEndStation(endStation);
                Calendar backStartCalendar = TimeUtils.getCalendarFromSQLTimer(
                        canceledSegmentInfo.getStartDate(),
                        canceledSegmentInfo.getStartTime()
                );
                tripRequest.setBackYear(backStartCalendar.get(Calendar.YEAR));
                tripRequest.setBackMonth(backStartCalendar.get(Calendar.MONTH) + 1);
                tripRequest.setBackDay(backStartCalendar.get(Calendar.DAY_OF_MONTH));
                tripRequest.setBackHour(backStartCalendar.get(Calendar.HOUR_OF_DAY));
                tripRequest.setBackMinute(backStartCalendar.get(Calendar.MINUTE));
                tripRequest.setBackStartStation(canceledSegmentInfo.getStartStation());
            }

            endStation = canceledSegmentInfo.getEndStation();
        }

        if (isFast) {
            tripRequest.setFast(true);
            tripRequest.setNormal(false);
        } else {
            tripRequest.setFast(false);
            tripRequest.setNormal(true);
        }

        if (!directionChanged) {
            tripRequest.setGoEndStation(endStation);
        } else {
            tripRequest.setBackEndStation(endStation);
        }

        return tripRequest;
    }

    private static List<InputSegmentInfo> generateInputSegmentInfoListFromTripInfo(
            TripInfo tripInfo) {

        List<InputSegmentInfo> inputSegmentInfoList = new ArrayList<>();

        for (SearchOutputSegmentInfo searchOutputSegmentInfo : tripInfo.getSegments()) {
            inputSegmentInfoList.add(generateInputSegmentInfoFromSearchOutput(
                    searchOutputSegmentInfo
            ));
        }

        return inputSegmentInfoList;
    }

    private static InputSegmentInfo generateInputSegmentInfoFromSearchOutput(
            SearchOutputSegmentInfo searchOutputSegmentInfo) {

        Calendar startCalendar = TimeUtils.getCalendarFromString(
                searchOutputSegmentInfo.getStartDay(),
                searchOutputSegmentInfo.getStartTime()
        );

        Calendar endCalendar = TimeUtils.getCalendarFromString(
                searchOutputSegmentInfo.getEndDay(),
                searchOutputSegmentInfo.getEndTime()
        );

        return new InputSegmentInfo(
                searchOutputSegmentInfo.getTrainName(),
                searchOutputSegmentInfo.isFast(),
                startCalendar.get(Calendar.YEAR),
                startCalendar.get(Calendar.MONTH) + 1,
                startCalendar.get(Calendar.DAY_OF_MONTH),
                startCalendar.get(Calendar.HOUR_OF_DAY),
                startCalendar.get(Calendar.MINUTE),
                endCalendar.get(Calendar.HOUR_OF_DAY),
                endCalendar.get(Calendar.MINUTE),
                searchOutputSegmentInfo.getStartStation(),
                searchOutputSegmentInfo.getEndStation(),
                searchOutputSegmentInfo.getPrice()
        );
    }

    public static boolean generateDirection(String trainName) {

        char direction = trainName.charAt(0);

        return direction == 'S';
    }

    public static boolean generateFast(String trainName) {

        return trainName.charAt(trainName.length() - 1) == '0' &&
                trainName.charAt(trainName.length() - 2) == '0';
    }
}