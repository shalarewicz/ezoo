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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.AnimalDAO;
import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;
import shala.ezoo.model.FeedingScheduleEditor;

@Controller
@RequestMapping("/addAnimal")
public class AddAnimalController {
    
    @Autowired
    private AnimalDAO animalDao;
    
    @Autowired
    private FeedingScheduleDAO feedingDao;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(FeedingSchedule.class, "feedingSchedule", new FeedingScheduleEditor());
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String showAnimalForm(Model model) {
        model.addAttribute("animal", new Animal());
        
        model.addAttribute("animalStatuses", Animal.HEALTH_STATUSES);
        model.addAttribute("animalTypes", Animal.TYPES);
        model.addAttribute("animalAction", "addAnimal");
        return "animalForm";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String addAnimal(@Valid @ModelAttribute("animal") Animal animal, Errors errors, Model model,  HttpSession session) {
       
        if (errors.hasErrors()) {
            model.addAttribute("message", "Please correct the specified fields.");
        } else {
            try {
                // If a feeding schedule id was provided check to see if the id was valid
                if (animal.getFeedingSchedule() == null || feedingDao.getScheduleByID(animal.getFeedingSchedule().getScheduleId()) != null) {
                    // Save the animal to the database
                    if (animalDao.saveAnimal(animal)) {
                        session.setAttribute("message", "Animal Successfully Saved");
                        session.setAttribute("messageClass", "alert-success");
                        return "redirect:/animalCare";
                    } else {
                        session.setAttribute("message", "Id of " + animal.getAnimalID() + " is already in use");
                    }
                } 
            else {
                session.setAttribute("message", "Invalid Feeding Schedule ID: " + animal.getFeedingSchedule().getScheduleId() + ".");
                }
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                session.setAttribute("message",  "There was a problem creating the animal at this time");
            } 
        }
        session.setAttribute("messageClass", "alert-danger");
        model.addAttribute("animalStatuses", Animal.HEALTH_STATUSES);
        model.addAttribute("animalTypes", Animal.TYPES);
        return "animalForm";
    }

    
}
