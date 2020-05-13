package test.shala.ezoo.dao;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import shala.ezoo.dao.AnimalDAO;
import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;
import test.shala.ezoo.config.TestConfig;

public class AnimalRepositoryTest {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
		AnimalDAO repo = (AnimalDAO) context.getBean(AnimalDAO.class);
		FeedingScheduleDAO frepo = (FeedingScheduleDAO) context.getBean(FeedingScheduleDAO.class);
		
		long testId = 2000L;
		long testId2 = 2001L;
		long testFsId = 2001L;
		
		try {
    		// Add an animal
    		System.out.println("\nAdding animal");
            Animal a = new Animal();
            a.setName("Test Animal 2");
            a.setAnimalID(testId);
            repo.saveAnimal(a);
            
            try {
                repo.saveAnimal(a);
            } catch (DataIntegrityViolationException e) {
                System.out.println("Test Adding Duplicate...PASS");
            }
            
    		// Get an animal
                System.out.println("\nRetrieving animal");
        		Animal animal = repo.getAnimalByID(testId);
        		System.out.println(animal);
        		if (!animal.equals(a)) {
        		    repo.removeAnimal(testId);
        		    System.err.println("Failed to Retrieve the right animal");
        		    System.err.println("Retrieved: " + animal);
        		    System.err.println("Expected: " + a);
        		    assert false;
        		}
        		System.out.println("PASS");
    		
    		// Update the animal
        		System.out.println("\nUpdating an animal");
        		a.setHeight(25.0);
        		
        		FeedingSchedule fs = new FeedingSchedule(testFsId, "12:00 AM","Daily", "Dairy", "notes");
        		frepo.saveSchedule(fs);
        		
        		a.setFeedingSchedule(fs);
        		repo.updateAnimal(a);
        		Animal updatedAnimal = repo.getAnimalByID(testId);
        		if (!updatedAnimal.equals(a)) {
        		    repo.removeAnimal(testId);
        		    frepo.removeSchedule(testFsId);
                    System.err.println("Failed to Update the right animal");
                    System.err.println("Retrieved: " + updatedAnimal);
                    System.err.println("Expected: " + a);
                    assert false;
        		}
    		
    		// Get all animals with given Feeding Schedule
        		System.out.println("\nGet animals by feeding schedulel");
        	    // Add an animal without the fs
        		Animal a2 = new Animal();
        		a2.setAnimalID(testId2);
        		repo.saveAnimal(a2);
        		List<Animal> allById = repo.getAllAnimals(fs.getScheduleId());
        		if (!allById.contains(a)) {
        		    System.err.println("Failed to retrieve animals by schedulId: " + testFsId);
        		    System.err.println("Retrieved: " + allById);
        		    assert false;
        		}
        		System.out.println("PASS");
    	        
                if (allById.contains(a2)) {
                    System.err.println("Failed to retrieve animals by schedulId. Result contains: " + a2.getFeedingSchedule().getScheduleId());
                    System.err.println("Retrieved: " + allById);
                    assert false;
                }
                
                allById.stream().forEach((x -> System.out.println(x)));
                System.out.println("PASS");
    			
    		// Get all animals
                System.out.println("\nGet All animals.");
        		List<Animal> all = repo.getAllAnimals();
        		
        		if (!all.contains(a) || !all.contains(a2)) {
        		    System.err.println("Failed to retrieve all animals");
                    System.err.println("Retrieved: " + all);
                    assert false;
        		}
        		all.stream().forEach((x -> System.out.println(x)));
        		System.out.println("PASS");
        		
           // Remove Animals Schedule
        		System.out.println("\nRemove Schedule.");
        		repo.removeSchedule(testId);
        		System.out.println("PASS");
        		
        		
		} catch (Exception e) {
		    e.printStackTrace();
		}
		finally {
		
//    		repo.removeAnimal(testId);
    		repo.removeAnimal(testId2);
//    		frepo.removeSchedule(testFsId);
    		
    		((AnnotationConfigApplicationContext) context).close();
		}
		
	}
}
