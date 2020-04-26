package shala.ezoo.controllers.animal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shala.ezoo.dao.AnimalDAO;
import shala.ezoo.model.Animal;

@Controller
public class AnimalCareController {
    
    @Autowired
    private AnimalDAO dao;

    @RequestMapping(value="/animalCare", method=RequestMethod.GET)
    public String viewHome(Model model) {
        List<Animal> animals = dao.getAllAnimals();
        model.addAttribute("animals", animals);
        model.addAttribute("animal", new Animal());
        model.addAttribute("updateAnimal", new Animal());
        model.addAttribute("deleteId", new Animal());
        
        Animal largest = new Animal();
        for (Animal a : animals)
            if (a.getWeight() > largest.getWeight())
                largest = a;
        model.addAttribute("largestAnimal", largest);
        
        Animal longest = new Animal();
        for (Animal a : animals)
            if (a.getName().length() > longest.getName().length())
                longest = a;
        model.addAttribute("longestNamedAnimal", longest);
        
        System.out.println("Getting home");
        return "animalCareHome";
    }
    
    
}
