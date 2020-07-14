package shala.ezoo.controllers.feedingSchedule;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.FeedingScheduleDAO;

@Controller
public class DeleteFeedingScheduleController {
    
    @Autowired
    private FeedingScheduleDAO dao;

    @RequestMapping(value = "feedingSchedule/delete/{scheduleId}", method = RequestMethod.POST)
    public String deleteSchedule(@PathVariable long scheduleId, HttpSession session) {
        
        if (dao.removeSchedule(scheduleId) != null) {
            session.setAttribute("message", "Feeding Schedule successfully deleted");
            session.setAttribute("messageClass", "alert-success");
        } else {
            session.setAttribute("message", "Failed to delete Feeding Schedule");
            session.setAttribute("messageClass", "alert-danger");
        }
        
        
        return "redirect:/feedingSchedule/home";
        
    }

}
