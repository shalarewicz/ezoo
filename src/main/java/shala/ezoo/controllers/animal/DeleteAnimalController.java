package shala.ezoo.controllers.animal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.AnimalDAO;

@Controller
public class DeleteAnimalController {
    @Autowired
    private AnimalDAO animalDao;
    
    @RequestMapping(value="animal/delete/{deleteId}", method=RequestMethod.POST)
    public String deleteAnimalPOST(@PathVariable("deleteId") long id, HttpSession session) {
        if (animalDao.removeAnimal(id) != null) {
            session.setAttribute("message", "Animal Successfully Deleted");
            session.setAttribute("messageClass", "alert-success");
        } else {
            session.setAttribute("messageClass", "alert-danger");
            session.setAttribute("message", "Failed to delete animal.");
        }
        
        return "redirect:/animal/home";
    }
}
