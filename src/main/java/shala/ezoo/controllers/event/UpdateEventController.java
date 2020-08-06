package shala.ezoo.controllers.event;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.EventDao;
import shala.ezoo.model.Event;

@RequestMapping("/event/update/{eventId}")
@Controller
public class UpdateEventController {
    
    @Autowired
    EventDao dao;
    
    @RequestMapping(method = RequestMethod.GET)
    public String showEventForm(@PathVariable("eventId") long eventId, Model model, Principal principal, HttpSession session) {
        
        try {
            Event e = dao.getEvent(eventId);
            
            if (principal == null || !principal.getName().equals(e.getCreator())) {
                session.setAttribute("message", "You are not authorized to edit this event");
                session.setAttribute("messageClass", "alert-danger");
                return "redirect:/user/profile";
            } else {
                model.addAttribute("event", e);
                return "eventForm";
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Could not retrieve event information. Please try again later");
            session.setAttribute("messageClass", "alert-danger");
            return "redirect:/user/profile";            
        }
        
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String updateEvent(@PathVariable("eventId") long eventId, @Valid @ModelAttribute Event event, Errors errors, Model model, Principal principal, HttpSession session) {
        
        try {
            Event e = dao.getEvent(eventId);
            event.setEventId(eventId);
            event.setCreator(e.getCreator());
            e = event;
            
            if (principal == null || !principal.getName().equals(e.getCreator())) {
                session.setAttribute("message", "You are not authorized to edit this event");
                session.setAttribute("messageClass", "alert-danger");
            } else {
                dao.updateEvent(e);
                session.setAttribute("message", "Event updated successfully");
                session.setAttribute("messageClass", "alert-success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Could not update event at this time. Please try again later");
            session.setAttribute("messageClass", "alert-danger");
        }
        return "redirect:/user/profile";            
        
    }

}
