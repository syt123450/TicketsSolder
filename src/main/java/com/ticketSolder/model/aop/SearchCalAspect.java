package com.ticketSolder.model.aop;

import com.ticketSolder.model.dao.mysql.SearchLogDao;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by ss on 2017/12/20.
 */

@Order(0)
@Aspect
@Component
public class SearchCalAspect {

    @Autowired
    private SearchLogDao searchLogDao;

    @Pointcut("execution(public * com.ticketSolder.presenter.SearchPresenter.*(..))")
    public void SearchCalculation(){}

    @Before("SearchCalculation()")
    public void calculateSearchTimes() {
        searchLogDao.insertOrUpdateSearchLog();
    }
}
