package example.com.controller.common;

import example.com.entity.User;
import example.com.entity.UserRole;
import example.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PublicAuthorizationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PublicAuthorizationController (UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String getLoginPage () {
        return "public/authorization/login-page";
    }

    @GetMapping("/registration")
    public String getRegistrationPage () {
        return "public/authorization/registration-page";
    }

    @PostMapping("/registration")
    public String createUserAccount (@RequestParam(name = "name") String name,
                                     @RequestParam(name = "email") String email,
                                     @RequestParam(name = "password") String password) {
        String encodedPassword = passwordEncoder.encode(password);
        userService.save(new User(name, email, encodedPassword, UserRole.USER));
        return "redirect:/login";
    }
}
