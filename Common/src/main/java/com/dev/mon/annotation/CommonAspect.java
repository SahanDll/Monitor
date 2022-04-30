package com.dev.mon.annotation;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CommonAspect {

    @Autowired
    private MeterRegistry registry;
    private Timer timer;

    //@Pointcut("execution(* com.dev.mon..*(..))")
    @Around(value = "@annotation(matrix)")
    public Object logExecution(ProceedingJoinPoint pjp, Matrix matrix) throws Throwable {
        final String prefix = matrix.name().toUpperCase();
        final String start = prefix+"_START";
        final String end = prefix+"_END";
        Object methodReturn;
        timer = registry.timer(matrix.name());
        log.info(prefix);
        if(checkLogsEnabled(matrix)){
            methodReturn = performWithLogs(pjp, start, end);
        }else{
            methodReturn = performWithMatrix(pjp);
        }

        return methodReturn;
    }

    private Object performWithLogs(ProceedingJoinPoint pjp, String start, String end) throws Throwable {
        log.info(start);
        Object proceed = pjp.proceed();
        log.info(end);
        return proceed;
    }

    private Object performWithMatrix(ProceedingJoinPoint pjp) throws Throwable {
        return timer.record(() -> {
            Object proceed = null;
            try {
                proceed = pjp.proceed();
            } catch (Throwable throwable) {
                log.error("Method execution error");
            }
            return proceed;
        });

    }

    private boolean checkLogsEnabled(Matrix matrix){
        return matrix.enableLogs();
    }

}
