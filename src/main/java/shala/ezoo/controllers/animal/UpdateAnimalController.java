package shala.ezoo.controllers.animal;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.AnimalDAO;
import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;
import shala.ezoo.model.FeedingScheduleEditor;

@Controller
@RequestMapping("/animal/{animalId}")
public class UpdateAnimalController {
    @Autowired
    private AnimalDAO animalDao;
    
    @Autowired
    private FeedingScheduleDAO feedingDao;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(FeedingSchedule.class, "feedingSchedule", new FeedingScheduleEditor());
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String showAnimalForm(@PathVariable long animalId, Model model) {
        model.addAttribute("animal", animalDao.getAnimalByID(animalId));
        
        model.addAttribute("animalStatuses", Animal.HEALTH_STATUSES);
        model.addAttribute("animalTypes", Animal.TYPES);
        model.addAttribute("readonly", true);
        return "animalForm";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String updateAnimal(@Valid @ModelAttribute Animal animal, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            session.setAttribute("message", "Please correct the specified fields.");
        } else {
            try {
                // Replace temporary binding object with actual schedule. Temp object used due to validation occurring after binding. 
                if (animal.getFeedingSchedule() != null) {
                    animal.setFeedingSchedule(feedingDao.getScheduleByID(animal.getFeedingSchedule().getScheduleId()));
                }
                
                // Save the animal to the database
                animalDao.updateAnimal(animal);
                session.setAttribute("message", "Animal Successfully Updated");
                session.setAttribute("messageClass", "alert-success");
                return "redirect:/animalCare";
                
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                session.setAttribute("message",  "There was a problem updating the animal at this time");
            } 
        }
        session.setAttribute("messageClass", "alert-danger");
        model.addAttribute("animalStatuses", Animal.HEALTH_STATUSES);
        model.addAttribute("animalTypes", Animal.TYPES);
        model.addAttribute("readonly", true);
        return "animalForm";
    }
}
