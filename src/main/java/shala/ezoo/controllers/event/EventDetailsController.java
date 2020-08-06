package shala.ezoo.controllers.event;

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
@RequestMapping("/event/details/{eventId}")
public class EventDetailsController {
    
    @Autowired
    EventDao eventDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public String showEventDetails(@PathVariable("eventId") long eventId, Model model, HttpSession session) {
        
        try {
            Event e = eventDao.getEvent(eventId);
            if (e != null) {
                model.addAttribute("event", e);
                return "eventInfo";
            } else {
                session.setAttribute("message", "Event not found");
            }
        } catch (Exception e) {
            session.setAttribute("message", "Event information cannot be retrieved at this time.");
        }
        session.setAttribute("messageClass", "alert-danger");
        return "redirect:/event/home";
        
    }

}
