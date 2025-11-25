package example.com.aspects.CotrollersAspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrivateAccountControllerAspect {

    @Pointcut("within(example.com.controller.secured.PrivateAccountController)")
    public void privateAccountControllerMethods(){}

    @Before("privateAccountControllerMethods()")
    public void beforeControllerMethods(JoinPoint joinPoint){
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

    @After("privateAccountControllerMethods()")
    public void afterControllerMethods(JoinPoint joinPoint){
        System.out.println("After Controller method start: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "privateAccountControllerMethods()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex){
        System.out.println("Error in method");
    }
}
