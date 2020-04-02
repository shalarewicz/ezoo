package shala.ezoo.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;

public class FeedingScheduleDAOImplTest {
	FeedingScheduleDAO dao = new FeedingScheduleDAOImpl();
	AnimalDAO adao = new AnimalDaoImpl();
	List<FeedingSchedule> currentSchedules = dao.getAllSchedules();
	List<Animal> currentAnimals = adao.getAllAnimals();
	
	
	
	void getAllSchedulesTest() {
		System.out.println("Printing All schedules");
		List<FeedingSchedule> schedules = dao.getAllSchedules();
		assert currentSchedules.size() == schedules.size();
		assert currentSchedules.containsAll(schedules);
		
	    for (FeedingSchedule fs : schedules) {
	      System.out.println(fs);
	    }
	    
	    System.out.println("Done\n");
	}
	
	void addScheduleTest(FeedingSchedule schedule) {
		this.currentSchedules.add(schedule);
		
		System.out.println("Adding schedule..." + schedule);
		try {
			dao.saveSchedule(schedule);
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
		
		getAllSchedulesTest();
		System.out.println("done\n");
	}
	
	void removeScheduleTest(FeedingSchedule schedule) {
		System.out.println("Removing schedule..." + schedule);
		dao.removeSchedule(schedule.getScheduleId());
		this.currentSchedules.remove(schedule);
		getAllSchedulesTest();
		System.out.println("done\n");
		
		// TODO Test removed from animals
	}
	
	
	void setFeedingScheduleTest(FeedingSchedule schedule, Animal animal) {
		System.out.println("Setting schedule for..." + animal);
		this.currentSchedules.add(schedule);
		
		try {
			dao.setFeedingSchedule(schedule, animal);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to insert animal" + animal);
		}
		
		
		
		System.out.println("done\n");
	}
	
	
	void updateScheduleTimeTest(FeedingSchedule schedule, String expected) {
		System.out.println("Updating schedule for..." + schedule);
		System.out.println("Changing time to " + expected);
		schedule.setFeedingTime(expected);
		try {
			dao.updateSchedule(schedule);
			assert expected.contentEquals(dao.getScheduleByID(schedule.getScheduleId()).getFeedingTime());
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
		
		System.out.println("done\n");
	}
	
	void updateScheduleRecurrenceTest(FeedingSchedule schedule, String expected) {
		System.out.println("Updating schedule for..." + schedule);
		System.out.println("Changing recurrence to " + expected);
		schedule.setRecurrence(expected);
		try {
			dao.updateSchedule(schedule);
			assert expected.equals(dao.getScheduleByID(schedule.getScheduleId()).getRecurrence());
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
		
		System.out.println("done\n");
	}
	
	void updateScheduleFoodTest(FeedingSchedule schedule, String expected) {
		System.out.println("Updating schedule for..." + schedule);
		System.out.println("Changing recurrence to " + expected);
		schedule.setFood(expected);
		try {
			dao.updateSchedule(schedule);
			assert expected.equals(dao.getScheduleByID(schedule.getScheduleId()).getFood());
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
		
		System.out.println("done\n");
	}
	
	void updateScheduleNotesTest(FeedingSchedule schedule, String expected) {
		System.out.println("Updating schedule for..." + schedule);
		System.out.println("Changing notes to " + expected);
		schedule.setNotes(expected);
		try {
			dao.updateSchedule(schedule);
			assert expected.equals(dao.getScheduleByID(schedule.getScheduleId()).getNotes());
		} catch (Exception e) {
			e.printStackTrace();
			assert false;
		}
		
		System.out.println("done\n");
	}
	
	public static void main(String[] args){
	   FeedingSchedule test1 = new FeedingSchedule(101L, "time1", "recurr1", "food1", "notes1");
	   FeedingSchedule test2 = new FeedingSchedule(102L, "time2", "recurr2", "food2", "notes2");
	   FeedingSchedule test3 = new FeedingSchedule(103L, "time3", "recurr3", "food3", "notes3");
	   FeedingSchedule test4 = new FeedingSchedule(104L, "time4", "recurr4", "food4", "notes4");
	   
	   Animal animal = new Animal();
	   FeedingScheduleDAOImplTest test = new FeedingScheduleDAOImplTest();
	   test.getAllSchedulesTest();
	   
	   test.addScheduleTest(test2);
	   test.getAllSchedulesTest();

	   test.removeScheduleTest(test2);
	   test.getAllSchedulesTest();
	   
	   test.addScheduleTest(test3);
	   test.addScheduleTest(test4);
	   test.getAllSchedulesTest();
	   
	   test.updateScheduleFoodTest(test4, "new food");
	   test.updateScheduleNotesTest(test4, "new note");
	   test.updateScheduleRecurrenceTest(test4, "new recurrence");
	   test.updateScheduleTimeTest(test4, "new time");
	   
	}
}
