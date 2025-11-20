package example.com.controller.exeption;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@Controller
public class GlobalExeptionHandler implements ErrorController {

    @RequestMapping("/error")
    public String getErrorPage(){
        return "public/error/error-page";
    }
    @ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable throwable){
        return "redirect:/error";
    }
}
