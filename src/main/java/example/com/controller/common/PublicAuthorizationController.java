package example.com.controller.common;

import example.com.entity.User;
import example.com.entity.UserRole;
import example.com.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/")
public class PublicAuthorizationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PublicAuthorizationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("IsAuthenticationFailed", true);
        }
        return "public/authorization/login-page";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "public/authorization/registration-page";
    }

    @PostMapping("/registration")
    public String createUserAccount(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String password) {
        String encodedPassword = passwordEncoder.encode(password);
        userService.save(new User(name, email, encodedPassword, UserRole.USER));


        forceAutoLogin(email, password);

        return "redirect:/account";
    }

    private void forceAutoLogin(String email, String rawPassword) {
        var auth = new UsernamePasswordAuthenticationToken(
                email,
                rawPassword,
                Collections.singleton(UserRole.USER.toAuthority())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
