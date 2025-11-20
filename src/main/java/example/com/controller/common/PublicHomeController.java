package example.com.controller.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicHomeController {

    @RequestMapping("/")
    public String getHomePage(){
        return "/public/home-page";

    }
}
