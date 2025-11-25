package example.com.aspects.ServicesAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceAspect {

    @Pointcut("execution(* example.com.service.UserService.*(..))")
    public void userServiceMethods () {
    }

    @Before("userServiceMethods()")
    public void beforeUserServiceMethods (JoinPoint joinPoint) {
        System.out.println("Run UserService method run: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "userServiceMethods()", throwing = "ex")
    public void afterThrowing (JoinPoint joinPoint, Throwable ex) {
        System.out.println("Error in method: " + joinPoint.getSignature().getName());
        System.out.println("Cause: " + ex.getMessage());
        ex.printStackTrace();
    }

    @Around("userServiceMethods()")
    public Object measureTime (ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println(
                "Method: " + joinPoint.getSignature().getName() +
                        " | Time: " + (end - start) + " ms"
        );
        return result;

    }
}
