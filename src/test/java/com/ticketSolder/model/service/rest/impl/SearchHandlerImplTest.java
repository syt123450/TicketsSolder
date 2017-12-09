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

    @Test
    @Ignore
    public void testSearchTripInfo() {
        TripRequest tripRequest = new TripRequest(
               false,
                true,
                false,
                2017,
                12,
                10,
                7,
                30,
                'B',
                'S',
                0,
                0,
                0,
                0,
                0,
                'A',
                'B',
                3,
                false
        );

        TripSearchResult tripSearchResult = searchHandler.searchTripInfo(tripRequest);

        System.out.println(tripSearchResult);

        tripSearchResult.getGoTripInfoAggregation().getFastTrainTrips().forEach(System.out::println);
    }
}