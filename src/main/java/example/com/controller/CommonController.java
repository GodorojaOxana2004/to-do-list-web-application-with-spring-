package example.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import example.com.entity.RecordStatus;
import example.com.entity.dto.RecordsContainerDto;
import example.com.service.RecordService;

@Controller
public class CommonController {
    private final RecordService recordService;

    @Autowired
    public CommonController (RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping("/home")
    public String getMainPage (Model model, @RequestParam(name = "filter",required = false) String filterMode) {
        RecordsContainerDto containerDto = recordService.findAllRecords(filterMode);
        model.addAttribute("numberDoneRecords", containerDto.getNumberDoneRecords());
        model.addAttribute("numberActiveRecords", containerDto.getNumberActiveRecords());
        model.addAttribute("records", containerDto.getRecords());
        return "main-page";
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public String addRecord (@RequestParam String taskDescription ) {
        recordService.saveRecord(taskDescription);
        return "redirect:/home";
    }

    @RequestMapping("/")
    public String redirectToHomePage () {
        return "redirect:/home";

    }

    @RequestMapping(value = "/markDone", method = RequestMethod.POST)
    public String taskDone(@RequestParam int id){
        recordService.updateRecordStatus(id, RecordStatus.DONE);
        return  "redirect:/home";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String taskDelete(@RequestParam int id) {
        recordService.deleteRecord(id);
        return "redirect:/home";
    }

}
