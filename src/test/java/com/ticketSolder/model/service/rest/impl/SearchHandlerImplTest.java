package com.ticketSolder.model.service.rest.impl;

import com.ticketSolder.model.bean.trip.TripRequest;
import com.ticketSolder.model.bean.trip.TripSearchResult;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ss on 2017/12/5.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchHandlerImplTest {

    @Autowired
    private SearchHandlerImpl searchHandler;

//    private boolean round;
//    private boolean fast;
//    private boolean normal;
//
//    private int goYear;
//    private int goMonth;
//    private int goDay;
//    private int goHour;
//    private int goMinute;
//    private char goStartStation;
//    private char goEndStation;
//
//    private int backYear;
//    private int backMonth;
//    private int backDay;
//    private int backHour;
//    private int backMinute;
//    private char backStartStation;
//    private char backEndStation;
//
//    private int connections;
//    private boolean exactly;

    @Test
    @Ignore
    public void testSearchTripInfo() {
        TripRequest tripRequest = new TripRequest(
               false,
                false,
                true,
                2017,
                12,
                3,
                7,
                30,
                'A',
                'B',
                0,
                0,
                0,
                0,
                0,
                'A',
                'B',
                2,
                true
        );

        TripSearchResult tripSearchResult = searchHandler.searchTripInfo(tripRequest);

        System.out.println(tripSearchResult);

        tripSearchResult.getGoTripInfoAggregation().getNormalTrainTrips().forEach(System.out::println);
    }
}