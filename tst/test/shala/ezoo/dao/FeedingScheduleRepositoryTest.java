package test.shala.ezoo.dao;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import shala.ezoo.config.Config;
import shala.ezoo.dao.AnimalDAO;
import shala.ezoo.dao.FeedingScheduleDAO;
import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;

public class FeedingScheduleRepositoryTest {
	
	
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		FeedingScheduleDAO repo = context.getBean(FeedingScheduleDAO.class);
		AnimalDAO aRepo = context.getBean(AnimalDAO.class);
		long testId = 2000L;
		long testId2 = 2001L;
		long testId3 = 2002L;
		long notPresentId = 3000L;
		long animalId = 2000L;
		long animalId2 = 2001L;
		
		try {
            // Add a schedule
		    System.out.println("\nAdding schedule");
    		    FeedingSchedule fs = new FeedingSchedule();
    		    fs.setScheduleId(testId);
    		    try {
    		        repo.saveSchedule(fs);
    		    } 
    		    catch (DataIntegrityViolationException e) {
    		        throw new RuntimeException("FAILED adding schedule");
    	        } 
    		    
    		    try {
                    repo.saveSchedule(fs);
                } catch (DataIntegrityViolationException e) {
                    System.out.println("Test Adding Duplicate...PASS");
                }
    		    
		    System.out.print("Done");
		    
		    // Get a schedule
		    System.out.println("\nGetting a schedule");
    		    FeedingSchedule schedule = repo.getScheduleByID(testId);
    		    if (!fs.equals(schedule)) {
    		        throw new RuntimeException("FAILED getScheduleByID");
    		    }
    		    if (repo.getScheduleByID(notPresentId) != null) {
    		        throw new RuntimeException("FAILED getScheduleByID(Null)");
    		    }
		    System.out.println("PASS");
		    
		    // Update a schedule
		    System.out.println("\nUpdating schedule");
    		    fs.setFeedingTime("10:00 PM");
    		    repo.updateSchedule(fs);
    		    FeedingSchedule updatedFs = repo.getScheduleByID(fs.getScheduleId());
    		    if (!fs.equals(updatedFs)) {
                    throw new RuntimeException("FAILED to update schedule");
                }
		    System.out.println("PASS");
            
            // Remove a schedule
		    System.out.println("\nRemoving Schedule");
    		    repo.removeSchedule(testId);
    		    if (repo.getScheduleByID(testId) != null) {
                    throw new RuntimeException("FAILED to remove schedule");
                }
		    System.out.println("PASS");
            
            // Get all schedules
		    System.out.println("\nGet all schedules");
    		    // Add schedule
    		    FeedingSchedule s = new FeedingSchedule(testId, "Monthly", "Meat","", "");
    		    repo.saveSchedule(s);
    		    List<FeedingSchedule> all = repo.getAllSchedules();
    		    all.stream().forEach((x -> System.out.println(x)));
    		    if (!all.contains(s)) {
    		        throw new RuntimeException("Failed to get all schedules");
    		    }
    		   
    		System.out.println("PASS");
    		
    		// Set a feeding Schedule
    		    // TODO Test Atomicity of the op? 
    		System.out.println("\nSetting schedule for objects");
        		Animal a = new Animal();
        		FeedingSchedule newSchedule = new FeedingSchedule();
        		newSchedule.setScheduleId(4001L);
        		a.setAnimalID(4001L);
        		repo.setFeedingSchedule(newSchedule, a);
        		
        		if (!aRepo.getAnimalByID(4001L).getFeedingSchedule().equals(newSchedule)) {
        		    throw new RuntimeException("FAILED to set schedule for objects");
        		}
    		
    		System.out.println("PASS");
    		
    		// Set a feeding Schedule for ids
            System.out.println("Setting schedule for ids");
                Animal a2 = new Animal();
                a2.setAnimalID(animalId2);
                aRepo.saveAnimal(a2);
                
                FeedingSchedule fs2 = new FeedingSchedule();
                fs2.setScheduleId(testId2);
                repo.saveSchedule(fs2);
                
                repo.setFeedingSchedule(testId2, animalId2);
                
                if (!aRepo.getAnimalByID(animalId2).getFeedingSchedule().equals(fs2)) {
                    throw new RuntimeException("FAILED to set schedule for ids");
                }
                
             // Set a feeding Schedule if schedule not present
                System.out.println("Setting schedule for ids");
                    
                    FeedingSchedule fs3 = new FeedingSchedule();
                    fs2.setScheduleId(testId3);
                    
                    repo.setFeedingSchedule(fs3, a2);
                    
                    if (!aRepo.getAnimalByID(animalId2).getFeedingSchedule().equals(fs3)) {
                        throw new RuntimeException("FAILED to set schedule for ids");
                    }
            
    		System.out.println("PASS");
    		
		} catch (Exception e) {
		    
		   e.printStackTrace();
		   
		} finally {
		    
		    aRepo.removeAnimal(animalId2);
		    aRepo.removeAnimal(animalId);
		    repo.removeSchedule(testId);
		    repo.removeSchedule(testId2);
		    repo.removeSchedule(testId3);
		    
		    ((AnnotationConfigApplicationContext) context).close();
		}
		
	}
}
