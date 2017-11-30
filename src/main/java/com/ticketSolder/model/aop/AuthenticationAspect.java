package com.ticketSolder.model.aop;

import com.ticketSolder.model.bean.user.AuthenticationResult;
import com.ticketSolder.model.domain.mysql.UserInfo;
import com.ticketSolder.model.service.rest.UserHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ss on 2017/11/16.
 */

@Aspect
@Component
public class AuthenticationAspect {

    private static final String INVALID_MASSAGE = "Invalid user.";

    @Autowired
    private UserHandler userHandler;

    @Pointcut("execution(public * com.ticketSolder.model.service.rest.helper.TransactionHelper.*(..))")
    public void Transaction(){}

    @Before("Transaction()")
    public void authenticate(JoinPoint joinPoint) throws Exception {
        System.out.println(joinPoint.getArgs()[0]);
        UserInfo userInfo = (UserInfo) joinPoint.getArgs()[0];
        AuthenticationResult authenticationResult = userHandler.authenticate(
                userInfo.getUserName(),
                userInfo.getPassword());

        if (!authenticationResult.isResult()) {
            throw new Exception(INVALID_MASSAGE);
        }
    }
}