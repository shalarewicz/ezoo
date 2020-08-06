package shala.ezoo.controllers.event;

import java.security.Principal;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.EventDao;
import shala.ezoo.dao.user.UserDao;
import shala.ezoo.model.Event;

@RequestMapping("/event/cancel/{eventId}")
@Controller
public class CancelEventController {
    
    @Autowired
    EventDao eventDao;
    
    @Autowired
    UserDao userDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public String showDeleteConfirmation(@PathVariable("eventId") long eventId, Model model, HttpSession session, Principal principal) {
        Event e = eventDao.getEvent(eventId);
        if (e.getCreator().contentEquals(principal.getName())) {
            model.addAttribute("event", e);
            return "eventCancel";
        } else {
            session.setAttribute("message", "You are not authorized to delete this event.");
            session.setAttribute("messageClass", "alert-danger");
            return "redirect:/";
        }
        
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String deleteEvent(@PathVariable("eventId") long eventId, HttpSession session) {
        
        try {
            Event e = eventDao.getEvent(eventId);
            if (e.getTime().compareTo(LocalDateTime.now()) > 0) {
                eventDao.removeEvent(eventId);
                session.setAttribute("message", "Event successfully cancelled");
                session.setAttribute("messageClass", "alert-success");
            } else {
                session.setAttribute("message", "Cannot cancel past events");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "There was a problem cancelling the event at this time. ");
            session.setAttribute("messageClass", "alert-danger");
        }
        
        return "redirect:/user/profile";
        
    }
}
