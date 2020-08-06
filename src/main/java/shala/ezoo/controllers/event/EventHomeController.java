package shala.ezoo.controllers.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.EventDao;
import shala.ezoo.model.Event;

@Controller
@RequestMapping("/event/home")
public class EventHomeController {
    
    @Autowired
    private EventDao eventDao;
    
   
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
        List<Event> events = eventDao.getAllEvents();
        model.addAttribute("events", events);
        //TODO show future events only
        
        return "eventHome";
        
    }
    
}
