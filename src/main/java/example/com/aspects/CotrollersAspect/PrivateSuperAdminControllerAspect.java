package example.com.aspects.CotrollersAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrivateSuperAdminControllerAspect {
    @Pointcut("within(example.com.controller.secured.PrivateSuperAdminController)")
    public void privateSuperAdminControllerMethods(){}

    @Before("privateSuperAdminControllerMethods()")
    public void logStart(JoinPoint joinPoint){
        System.out.println("Start Controller Method: " + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if(args!=null && args.length>0){
            System.out.println("Args: ");
            for (Object arg : args){
                System.out.println(arg + " ");
            }
            System.out.println();
        }
    }

    @After("privateSuperAdminControllerMethods()")
    public void afterMethods(JoinPoint joinPoint){
        System.out.println("End controller method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "privateSuperAdminControllerMethods()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex){
        System.out.println("Error in method " + joinPoint.getSignature().getName());
        System.out.println("Cause" + ex);

    }
}
