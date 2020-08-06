package shala.ezoo.controllers.event;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.EventDao;
import shala.ezoo.model.Event;

@Controller
@RequestMapping("/event/cancelRSVP/{eventId}")
public class CancelEventRSVPController {
    
    @Autowired
    EventDao eventDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public String showCancelConfirmation(@PathVariable("eventId") long eventId, Model model, HttpSession session, Principal principal) {
        Event event = eventDao.getEvent(eventId);
        
        List<Event> events = eventDao.getEventsAttended(principal.getName());
        
        if (events.stream().anyMatch(e -> e.getEventId() == event.getEventId())) {
            model.addAttribute("event", event);
            return "eventRSVP";
        } else {
            session.setAttribute("message", "You are not registered for this event.");
            session.setAttribute("messageClass", "alert-danger");
            return "redirect:/user/profile";
        }
    }

    
    @RequestMapping(method = RequestMethod.POST)
    public String cancelEventRSVP(@PathVariable("eventId") long eventId, Model model, HttpSession session, Principal principal) {
            
        if (eventDao.removeEventUser(eventId, principal.getName())) {
            session.setAttribute("message", "Cancellation Successful");
            session.setAttribute("messageClass", "alert-success");
        } else {
            session.setAttribute("message", "You are not registered for this event.");
            session.setAttribute("messageClass", "alert-danger");
        }
        return "redirect:/user/profile";
    }
}
