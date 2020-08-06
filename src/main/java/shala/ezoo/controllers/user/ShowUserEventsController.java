package shala.ezoo.controllers.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.EventDao;

@Controller
@RequestMapping("/user/events")
public class ShowUserEventsController {

    @Autowired
    EventDao dao;
    
    @RequestMapping(method = RequestMethod.GET) 
    public String showUserEvents(Model model, Principal principal) {
        model.addAttribute("userEvents", dao.getEventsAttended(principal.getName()));
        model.addAttribute("createdEvents", dao.getAllEvents(principal.getName()));
        return "userEvents";
    }
}
