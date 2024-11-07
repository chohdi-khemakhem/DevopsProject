package tn.esprit.tpfoyer.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ConfigAOP {

    @Before("execution(* tn.esprit.tpfoyer.service.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Entering method: {}", methodName);
    }

    @After("execution(* tn.esprit.tpfoyer.service.*.add*(..))")
    public void logMethodOut(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Execution successful for method: {}", methodName);
    }

    @Around("execution(* tn.esprit.tpfoyer.service.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result;

        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            log.error("Exception in method: {} with message: {}", pjp.getSignature().getName(), throwable.getMessage());
            throw throwable; // Re-throw the exception to ensure the normal flow of exception handling
        }

        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Execution time of method {}: {} milliseconds", pjp.getSignature().getName(), elapsedTime);

        return result;
    }
}
