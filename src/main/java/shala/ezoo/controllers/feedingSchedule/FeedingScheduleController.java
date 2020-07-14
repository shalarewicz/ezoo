package shala.ezoo.controllers.feedingSchedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.FeedingSchedule;

@Controller
@RequestMapping("/feedingSchedule/home")
public class FeedingScheduleController {
    
    @Autowired
    private FeedingScheduleDAO dao;
    
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
       List<FeedingSchedule> schedules = dao.getAllSchedules(); 
       
       model.addAttribute("schedules", schedules);
       
       
       return "feedingSchedules";
    }
}
