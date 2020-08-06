package shala.ezoo.controllers.event;

import java.security.Principal;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.EventDao;
import shala.ezoo.model.Event;
import shala.ezoo.model.LocalDateTimeEditor;

@Controller
@RequestMapping("/event/create")
public class CreateEventController {
    
    @Autowired
    private EventDao eventDao;

    @RequestMapping(method = RequestMethod.GET)
     public String showEventForm(Model model) {
         model.addAttribute("event", new Event());
         return "eventForm";
     }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimeEditor());
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String saveEvent(@Valid @ModelAttribute Event event, Errors errors, Model model, HttpSession session, Principal principal) {
        event.setCreator(principal.getName());
        
        if (errors.hasErrors()) {
            model.addAttribute("message", "Please correct specified fields.");
        } else {
            try {
                event.setCreator(principal.getName());
                long id = eventDao.saveEvent(event);
                if (id == 0L) {
                    session.setAttribute("message", "Event already exists");
                } else {
                    session.setAttribute("message", "Event Successfully Saved");
                    session.setAttribute("messageClass", "alert-success");
                    return "redirect:/event/home";
                }
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                session.setAttribute("message", "There was a problem creating the event at this time.");
            }
        }
        session.setAttribute("messageClass", "alert-danger");
        return "eventForm";
    }
    
}
