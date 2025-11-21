package example.com.controller.secured;


import example.com.entity.User;
import example.com.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivateAdminController {

    private final UserService userService;

    public PrivateAdminController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getManagmentPage(Model model){
        User user = userService.getUser();
        model.addAttribute("userName",user.getName());
        return "private/admin/managment-page";
    }
}
