package shala.ezoo.controllers.feedingSchedule;

import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.AnimalDAO;
import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.Animal;
import shala.ezoo.model.AnimalSchedule;
import shala.ezoo.model.FeedingSchedule;
import shala.ezoo.model.FeedingScheduleEditor;

@Controller
public class AssignFeedingScheduleController {
    
    @Autowired
    private AnimalDAO animalDao;
    
    @Autowired
    private FeedingScheduleDAO feedingDao;
    
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(FeedingSchedule.class, "feedingSchedule", new FeedingScheduleEditor());
    }
    
    @RequestMapping(value = "/feedingSchedule/assign", method = RequestMethod.GET)
    public String showAssignPage(Model model) {
        List<FeedingSchedule> schedules = feedingDao.getAllSchedules();
        schedules.sort(new Comparator<FeedingSchedule>() {
            public int compare(FeedingSchedule o1, FeedingSchedule o2) {
                return ((Long) o1.getScheduleId()).compareTo(o2.getScheduleId());
            }
        });
        
        List<Animal> animals = animalDao.getAllAnimals();
        animals.sort(new Comparator<Animal>() {
                public int compare(Animal o1, Animal o2) {
                    return ( o1.getName().compareTo(o2.getName()));
                }
            });
        model.addAttribute("animals", animals);
        model.addAttribute("feedingSchedules", schedules);
        model.addAttribute("animalSchedule", new AnimalSchedule(new Animal(), new FeedingSchedule()));
        
        return "assignSchedule";
    }
    
    @RequestMapping(value = "/feedingSchedule/assign", method = RequestMethod.POST)
    public String assignSchedule(@ModelAttribute AnimalSchedule animalSchedule, HttpSession session) {
        try {        
            if (feedingDao.setFeedingSchedule(animalSchedule.getFeedingSchedule().getScheduleId(), animalSchedule.getAnimal().getAnimalID())) {
                session.setAttribute("message", "Schedule successfully assigned");
                session.setAttribute("messageClass", "alert-success");
                return "redirect:/feedingSchedule/assign";
            } 
        } catch (Exception e) {
            e.printStackTrace();
            
        } 
        session.setAttribute("message", "There was a problem assigning the schedule at this time");
        session.setAttribute("messageClass", "alert-danger");
        return "redirect:/feedingSchedule/assign";
    }
    
}
