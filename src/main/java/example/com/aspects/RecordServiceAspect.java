package example.com.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RecordServiceAspect {

    @Pointcut("execution(* example.com.service.RecordService.*(..))")
    public void recordServiceMethods() {}

    @Before("recordServiceMethods()")
    public void beforeRecordsMethods(JoinPoint joinPoint) {
        System.out.println("recordServiceMethods method run: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "recordServiceMethods()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("Error in method: " + joinPoint.getSignature().getName());
        System.out.println("Cause: " + ex.getMessage());
        ex.printStackTrace();
    }

    @Around("recordServiceMethods()")
    public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = pjp.proceed();

        long end = System.currentTimeMillis();

        System.out.println(
                "Method: " + pjp.getSignature().getName() +
                        " | Time: " + (end - start) + " ms"
        );

        return result;
    }
}
