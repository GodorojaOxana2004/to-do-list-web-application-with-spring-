package example.com.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PublicAuthorizationControllerAspect {

    @Pointcut("within(example.com.controller.common.PublicAuthorizationController)")
    public void publicAuthControllerMethods () {
    }

    @Before("publicAuthControllerMethods()")
    public void logStart (JoinPoint joinPoint) {
        System.out.println("Start controller method: " + joinPoint.getSignature().getName());

        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            System.out.println("Args: ");
            for (Object arg : args) {
                System.out.print(arg + " ");
            }
            System.out.println();
        }
    }

    @After("publicAuthControllerMethods()")
    public void logEnd (JoinPoint joinPoint) {
        System.out.println("End controller method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "publicAuthControllerMethods()", throwing = "ex")
    public void afterThrowing (JoinPoint joinPoint, Throwable ex) {
        System.out.println("Error: " + joinPoint.getSignature().getName() );
        System.out.println("Cause: " + ex);
    }

//    @Around("publicAuthControllerMethods()")
//    public Object logControllerResult(ProceedingJoinPoint pjp) throws Throwable{
//        System.out.println("Controller method: " + pjp.getSignature().getName());
//
//        Object result = pjp.proceed();
//
//        if(result!=null){
//            System.out.println("Returned view: " + result);
//        }
//
//        System.out.println("Controller method end: "+ pjp.getSignature().getName());
//        return result;
//    }

}

