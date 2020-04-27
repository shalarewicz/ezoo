package test.shala.ezoo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import shala.ezoo.config.Config;
import shala.ezoo.exceptions.ResourceNotFoundException;
import shala.ezoo.model.Animal;
import shala.ezoo.service.AnimalService;

public class AnimalServiceTest {
    
    public static void getByIDTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AnimalService service = (AnimalService) context.getBean(AnimalService.class);
        
        try {
            Animal animal = service.getAnimalByID(2000L);
            System.out.println(animal);
        } catch (ResourceNotFoundException e) {
            System.out.println("Caught not found exception");
            
        }
    }
    
    public static void main(String[] args) {
        getByIDTest();
    }
}
