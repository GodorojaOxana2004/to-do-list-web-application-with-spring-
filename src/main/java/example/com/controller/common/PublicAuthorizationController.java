package example.com.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class PublicAuthorizationController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "public/authorization/login-page";
    }

    @GetMapping("/registration")
    public String getRegistrationPage (){
        return "public/authorization/registration-page";
    }
}
