package com.luxoft.omalovanyi.aop.logging;

import com.luxoft.omalovanyi.reader.JsonReaderException;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * Created by maoleks on 6/10/2016.
 */

@Service
@Aspect
public class TimeLogger {

    private static final Logger logger = Logger.getLogger(TimeLogger.class);

    @Pointcut("execution(* *(..))")
    private void allMethods() {
    }

    @Around("allMethods() && @annotation(ShowTime)")
    public Object watchTime(ProceedingJoinPoint joinpoint) throws JsonReaderException{
        double start = System.currentTimeMillis();
        Object output = null;
        try {
            output = joinpoint.proceed();
        } catch (Throwable e) {
            throw new JsonReaderException(e.getMessage(),e);
        }
        double time = (System.currentTimeMillis() - start)/1000.0;
        if ("MainProcess.startProcess()".equals(joinpoint.getSignature().toShortString())) {
            logger.info("TOTAL EXECUTION TIME = [" + time + "] SECONDS");
        } else if ("JsonReader.read()".equals(joinpoint.getSignature().toShortString())) {
            logger.info("READING TIME = [" + time + "] SECONDS");
        } else if ("JsonReader.sort()".equals(joinpoint.getSignature().toShortString())) {
            logger.info("SORTING TIME = [" + time + "] SECONDS");
        } else if ("JsonReader.save()".equals(joinpoint.getSignature().toShortString())) {
            logger.info("PERSISTING TIME = [" + time + "] SECONDS");
        }
        return output;
    }

}


