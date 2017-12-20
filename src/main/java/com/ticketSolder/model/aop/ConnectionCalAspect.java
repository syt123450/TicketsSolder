package com.ticketSolder.model.aop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ticketSolder.model.bean.trip.TripRequest;
import com.ticketSolder.model.dao.mysql.SearchLogDao;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by ss on 2017/12/20.
 */

@Order(1)
@Aspect
@Component
public class ConnectionCalAspect {

    private Logger logger = Logger.getLogger(ConnectionCalAspect.class);
    private Gson gson = new GsonBuilder().create();
    private static final Map<Integer, List<String>> connectionColumnMap = new HashMap<Integer, List<String>>(){{
        put(0, new ArrayList<String>() {{
            add("connection0");
            add("average0");
        }});
        put(1, new ArrayList<String>() {{
            add("connection1");
            add("average1");
        }});
        put(2, new ArrayList<String>() {{
            add("connection2");
            add("average2");
        }});
    }};

    @Autowired
    private SearchLogDao searchLogDao;

    @Pointcut("execution(public * com.ticketSolder.presenter.SearchPresenter.*(..))")
    public void Connection(){}

    @Around("Connection()")
    public String calculateLatency(ProceedingJoinPoint proceedingJoinPoint) {

        logger.info("Test latency for search.");

        long startTime = Calendar.getInstance().getTimeInMillis();

        String searchResult = "";

        try {
            searchResult  = (String) proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        long latency = Calendar.getInstance().getTimeInMillis() - startTime;

        String requestBody = (String) proceedingJoinPoint.getArgs()[0];
        TripRequest tripRequest = gson.fromJson(requestBody, TripRequest.class);

        int connection = tripRequest.getConnections();
        List<String> connectionColumn = connectionColumnMap.get(connection);

        searchLogDao.updateConnectionLog(
                connectionColumn.get(0),
                connectionColumn.get(1),
                latency
        );

        return searchResult;
    }
}
