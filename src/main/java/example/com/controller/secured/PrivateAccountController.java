package example.com.controller.secured;

import example.com.entity.User;
import example.com.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import example.com.entity.RecordStatus;
import example.com.entity.dto.RecordsContainerDto;
import example.com.service.RecordService;

@Controller
@RequestMapping("/account")
public class PrivateAccountController {
    private final RecordService recordService;
    private final UserService userService;


    @Autowired
    public PrivateAccountController (RecordService recordService, UserService userService) {
        this.recordService = recordService;
        this.userService = userService;
    }

    @GetMapping
    public String getMainPage (Model model, @RequestParam(name = "filter", required = false) String filterMode) {
        User user = userService.getUser();
        RecordsContainerDto containerDto = recordService.findAllRecords(filterMode);
        model.addAttribute("userName",user.getName());
        model.addAttribute("numberDoneRecords", containerDto.getNumberDoneRecords());
        model.addAttribute("numberActiveRecords", containerDto.getNumberActiveRecords());
        model.addAttribute("records", containerDto.getRecords());
        return "private/account-page";
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public String addRecord (@RequestParam String taskDescription) {
        recordService.saveRecord(taskDescription);
        return "redirect:/account";
    }

    @RequestMapping(value = "/markDone", method = RequestMethod.POST)
    public String taskDone (@RequestParam int id) {
        recordService.updateRecordStatus(id, RecordStatus.DONE);
        return "redirect:/account";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String taskDelete (@RequestParam int id) {
        recordService.deleteRecord(id);
        return "redirect:/account";
    }


}
