package shala.ezoo.controllers.feedingSchedule;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.FeedingSchedule;

@Controller
public class CreateFeedingScheduleController {
    
    @Autowired
    private FeedingScheduleDAO dao;
    
    @RequestMapping(value = "/feedingSchedule/create", method = RequestMethod.GET)
    public String showFeedingScheduleForm(Model model) {
        
        model.addAttribute("feedingSchedule", new FeedingSchedule());
        model.addAttribute("recurrences", FeedingSchedule.RECURRENCES);
        
        return "feedingScheduleForm";
    }
    
    @RequestMapping(value = "/feedingSchedule/create", method = RequestMethod.POST) 
    public String createSchedule(@Valid @ModelAttribute("feedingSchedule") FeedingSchedule schedule, Errors errors, Model model, HttpSession session) {
        
        if (errors.hasErrors()) {
            model.addAttribute("message", "Please correct the specified fields");
        } else {
            try {
                if (dao.saveSchedule(schedule)) {
                    session.setAttribute("message", "Feeding Schedule Successfully Saved");
                    session.setAttribute("messageClass", "alert-success");
                    return "redirect:/feedingSchedules";
                } else {
                    session.setAttribute("message", "ID of " + schedule.getScheduleId() + " is already in use");
                }
            }
            catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                session.setAttribute("message",  "There was a problem creating the animal at this time");
            } 
        }
        session.setAttribute("messageClass", "alert-danger");  
        model.addAttribute("recurrences", FeedingSchedule.RECURRENCES);
        return "feedingScheduleForm"; //TODO This probably overwrites the entered responses
    }


}
