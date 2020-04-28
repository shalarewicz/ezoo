package shala.ezoo.controllers.feedingSchedule;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.AnimalDAO;
import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;

@Controller
public class AssignFeedingScheduleController {
    
    @Autowired
    private AnimalDAO animalDao;
    
    @Autowired
    private FeedingScheduleDAO feedingDao;
    
    @RequestMapping(value = "/feedingSchedule/assign", method = RequestMethod.GET)
    public String showAssignPage(Model model) {
        List<FeedingSchedule> schedules = feedingDao.getAllSchedules();
        List<Long> scheduleIds = schedules.stream().map((x -> x.getScheduleId()))
                .sorted()
                .collect(Collectors.toList());

        List<Animal> animals = animalDao.getAllAnimals();
        animals.sort(new Comparator<Animal>() {
                public int compare(Animal o1, Animal o2) {
                    // TODO Auto-generated method stub
                    return ( o1.getName().compareTo(o2.getName()));
                }
            });
        
        model.addAttribute("animals", animals);
        model.addAttribute("feedingSchedules", scheduleIds);
        
        
        return "assignSchedule";
    }
    
    @RequestMapping(value = "feedingSchedule/assign", method = RequestMethod.POST)
    public String assignSchedule(Model model, HttpServletRequest request) {
        
        long scheduleId = Long.parseLong(request.getParameter("feedingSchedule"));
        long animalId = Long.parseLong(request.getParameter("animal"));
//        try { TODO surround with try catch so end user doesn't see exception
        feedingDao.setFeedingSchedule(scheduleId, animalId); 
        request.getSession().setAttribute("message", "Schedule successfully assigned");
        request.getSession().setAttribute("messageClass", "alert-success");
        return "redirect:/feedingSchedule/assign";
//        } catch (Exception e){
//            e.printStackTrace();
//
//            //change the message
//            request.getSession().setAttribute("message", "There was a problem assigning the schedule at this time");
//            request.getSession().setAttribute("messageClass", "alert-danger");
//
//            request.getRequestDispatcher("assignSchedule.jsp").forward(request, response);
//
//        }
    }
}
