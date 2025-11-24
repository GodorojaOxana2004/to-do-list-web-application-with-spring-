package example.com.controller.secured;


import example.com.entity.User;
import example.com.entity.UserRole;
import example.com.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class PrivateAdminController {

    private final UserService userService;

    public PrivateAdminController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getManagmentPage (Model model) {
        User user = userService.getUser();

        model.addAttribute("userName", user.getName());
        List<User> candidatesToDelete;
        List<User> candidatesToUpgrade;
        if (user.getRole() == UserRole.SUPER_ADMIN) {
            candidatesToDelete = userService
                    .findAllByRoleInOrderById(Arrays.asList(UserRole.USER, UserRole.ADMIN));
            candidatesToUpgrade = candidatesToDelete.stream().filter(User::isSimpleUser).collect(Collectors.toList());
            model.addAttribute("candidatesToDelete", candidatesToDelete);
            model.addAttribute("candidatesToUpgrade",candidatesToUpgrade);
        } else {
            candidatesToDelete = userService
                    .findAllByRoleInOrderById(Collections.singleton(UserRole.USER));
        }
        model.addAttribute("candidatesToDelete", candidatesToDelete);
        return "private/admin/managment-page";
    }

    @PostMapping("/delete-user")
    public String deteteUser (@RequestParam int id) {
        Optional<User> userToBeDeletedOptional = userService.findById(id);

        if (userToBeDeletedOptional.isEmpty()) {
            return "redirect:/admin";
        }
        User userToBeDeleted = userToBeDeletedOptional.get();
        User curentUser = userService.getUser();

        if (userToBeDeleted.isSuperAdmin()) {
            return "redirect:/admin";
        } else if(userToBeDeleted.isAdmin() && !curentUser.isSuperAdmin()){
            return "redirect:/admin";
        }

        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
