package shala.ezoo.controllers.event;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.EventDao;

@Controller
@RequestMapping("/event/register/{eventId}")
public class RegisterEventAttendeeController {
    
    @Autowired
    EventDao eventDao;
    
    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(@PathVariable("eventId") long eventId, Principal principal, HttpSession session) {
        String username = principal.getName();
        
        try {
            if (eventDao.registerEventUser(eventId, username)) {
                session.setAttribute("message", "Registration successful");
                session.setAttribute("messageClass", "alert-success");
            } else {
                session.setAttribute("message", "Unable to register. Please check if you are already registered.");
                session.setAttribute("messageClass", "alert-danger");
            }
        } catch (Exception e){
            e.printStackTrace();
            session.setAttribute("message", "There was a problem registering the event at this time. Please try again later.");
            session.setAttribute("messageClass", "alert-danger");
        }
        return "redirect:/event/home";
        
    }

}
