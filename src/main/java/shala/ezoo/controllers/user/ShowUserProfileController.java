package shala.ezoo.controllers.user;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.EventDao;
import shala.ezoo.dao.user.UserDao;
import shala.ezoo.model.Event;

@Controller
@RequestMapping("/user/profile")
public class ShowUserProfileController {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired 
    private EventDao eventDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public String showAccountInfo(Model model, HttpSession session, Principal principal) {
        String username = principal.getName();
        
        try {
            if (!username.equals("")) {
                model.addAttribute("user", userDao.getUser(username));
                List<Event> events = eventDao.getEventsAttended(username);
                
                Comparator<Event> comp = new Comparator<Event>() {
                    
                  public int compare(Event o1, Event o2) {
                     return o1.getTime().compareTo(o2.getTime());
                  }
                };
                events.sort(comp);
                events.stream().filter((x -> x.getTime().compareTo(LocalDateTime.now())>= 0));
                
                List<Event> createdEvents = eventDao.getAllEvents(username);
                createdEvents.sort(comp);
                createdEvents.stream().filter((x -> x.getTime().compareTo(LocalDateTime.now())>= 0));
                
                model.addAttribute("userEvents",  events.size() >=5 ? events.subList(0, 5): events);
                model.addAttribute("createdEvents", createdEvents);
                
                return "userProfile";
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        session.setAttribute("message", "There was a problem retrieving your account informaiton."
                + " Please try again later");
        session.setAttribute("messageClass", "alert-danger");
        return "redirect:/";

    }
    
}
