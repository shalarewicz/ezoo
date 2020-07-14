package shala.ezoo.controllers.feedingSchedule;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.FeedingSchedule;

@Controller
public class UpdateFeedingScheduleController {
    @Autowired
    private FeedingScheduleDAO dao;
    
    @RequestMapping(value = "/feedingSchedule/{scheduleId}", method = RequestMethod.GET)
    public String showFeedingScheduleForm(@PathVariable long scheduleId, Model model) {
        
        model.addAttribute("feedingSchedule", dao.getScheduleByID(scheduleId));
        model.addAttribute("recurrences", FeedingSchedule.RECURRENCES);
        
        return "feedingScheduleForm";
    }
    
    @RequestMapping(value = "/feedingSchedule/{scheduleId}", method = RequestMethod.POST) 
    public String updateSchedule(@Valid @ModelAttribute("feedingSchedule") FeedingSchedule schedule, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Please correct the specified fields");
        } else {
            try {
                dao.updateSchedule(schedule);
                session.setAttribute("message", "Feeding Schedule Successfully Updated");
                session.setAttribute("messageClass", "alert-success");
                return "redirect:/feedingSchedule/home";
            }
            catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to update schedule");
            } 
        }
        session.setAttribute("messageClass", "alert-danger");  
        session.setAttribute("message", "Failed to update schedule.");
        model.addAttribute("recurrences", FeedingSchedule.RECURRENCES);
        return "feedingScheduleForm";
    }
}
