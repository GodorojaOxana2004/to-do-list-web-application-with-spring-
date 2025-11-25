package example.com.aspects.CotrollersAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrivateAdminControllerAspect {

    @Pointcut("within(example.com.controller.secured.PrivateAdminController)")
    public void privateAdminControllerMethods(){}

    @Before("privateAdminControllerMethods()")
    public void beforeStartMethods(JoinPoint joinPoint){
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

    @After("privateAdminControllerMethods()")
    public void afterMethods(JoinPoint joinPoint){
        System.out.println("End Controller methods: " + joinPoint.getSignature().getName());
    }
}
