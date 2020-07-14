package shala.ezoo.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import shala.ezoo.config.Config;
import shala.ezoo.dao.AnimalDAO;
import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;

public class LoggingAspectTest {
    
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AnimalDAO repo = context.getBean(AnimalDAO.class);
        FeedingScheduleDAO fsrepo= context.getBean(FeedingScheduleDAO.class);
        
        long testId = 2000L;
        
        Animal animal = new Animal();
        FeedingSchedule schedule = new FeedingSchedule();
        
        schedule.setScheduleId(testId);
        animal.setAnimalID(testId);
        try {
            repo.saveAnimal(animal);
            repo.getAnimalByID(testId);
            animal.setName("Stephan");
            repo.updateAnimal(animal);
            repo.getAllAnimals();
            
            fsrepo.saveSchedule(schedule);
            fsrepo.getScheduleByID(testId);
            schedule.setFood("Meat");
            fsrepo.updateSchedule(schedule);
            fsrepo.getAllSchedules();
            fsrepo.setFeedingSchedule(schedule, animal);
            repo.getAllAnimals(schedule.getScheduleId());
            
//            fsrepo.saveSchedule(schedule);
            
        } catch (DataIntegrityViolationException e) {
            System.out.println("Caught in outer block");
            // TODO Auto-generated catch block
            e.printStackTrace();
           
        }
//        repo.removeAnimal(testId);
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            repo.removeAnimal(testId);
            fsrepo.removeSchedule(testId);
        }
        
        System.out.println("Done");
    }
}
