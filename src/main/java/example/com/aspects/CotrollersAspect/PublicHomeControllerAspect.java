package example.com.aspects.CotrollersAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PublicHomeControllerAspect {

    @Pointcut("within(example.com.controller.common.PublicHomeController)")
    public void publicHomeControllerMethods(){}

    @Before("publicHomeControllerMethods()")
    public void logStart(JoinPoint joinPoint){
        System.out.println("Controller method start: " + joinPoint.getSignature().getName());

        Object[] args = joinPoint.getArgs();
        if(args!=null && args.length>0){
            System.out.println("Args: ");
            for (Object arg : args){
                System.out.println(arg + " ");
            }
            System.out.println();
        }

    }

    @After("publicHomeControllerMethods()")
    public void beforeMethods(JoinPoint joinPoint){
        System.out.println("End controller method: "+ joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "publicHomeControllerMethods()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex){
        System.out.println("Error in method: " + joinPoint.getSignature().getName());
        System.out.println("Cause: "+ ex);
    }


}
