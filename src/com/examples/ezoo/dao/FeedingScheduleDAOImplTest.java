package com.examples.ezoo.dao;

import java.util.HashSet;
import java.util.Set;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDAOImplTest {
	FeedingScheduleDAO dao = new FeedingScheduleDAOImpl();
	Set<FeedingSchedule> currentSchedules = new HashSet<FeedingSchedule>();
	Set<Animal> currentAnimals = new HashSet<Animal>();
	
	
	
	void getAllSchedulesTest() {
		System.out.println("Printing All schedules");
		Set<FeedingSchedule> schedules = dao.getAllSchedules();
		assert currentSchedules.size() == schedules.size();
		assert currentSchedules.equals(schedules);
		
	    for (FeedingSchedule fs : schedules) {
	      System.out.println(fs);
	    }
	    
	    System.out.println("Done\n");
	}
	
	void addScheduleTest(FeedingSchedule schedule) {
		this.currentSchedules.add(schedule);
		
		System.out.println("Adding schedule..." + schedule);
		try {
			dao.addSchedule(schedule);
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
	
	void getScheduleTest(Animal animal, FeedingSchedule expected) {
		System.out.println("\nFetching Schedule for...." + animal);
		FeedingSchedule actual = dao.getSchedule(animal);
		assert actual.equals(expected);
		System.out.println("done\n");
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
	
	void removeScheduleTest(Animal animal) {
		System.out.println("Removing schedule for..." + animal);
		dao.removeSchedule(animal);
		
		long currentID = animal.getAnimalID();
		for (Animal a : this.currentAnimals) {
			if (currentID == a.getAnimalID()) {
				assert a.getFeedingSchedule() == 0L;
			}
		}
		
		System.out.println("done\n");
	}
	
	void updateScheduleTimeTest(FeedingSchedule schedule, String expected) {
		System.out.println("Updating schedule for..." + schedule);
		System.out.println("Changing time to 10 AM");
		schedule.setFeedingTime(expected);
		try {
			dao.updateSchedule(schedule.getScheduleId(), schedule);
			Animal animal = new Animal();
			animal.setFeedingSchedule(schedule.getScheduleId());
			assert expected == dao.getSchedule(animal).getFeedingTime();
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
			dao.updateSchedule(schedule.getScheduleId(), schedule);
			Animal animal = new Animal();
			animal.setFeedingSchedule(schedule.getScheduleId());
			assert expected == dao.getSchedule(animal).getFeedingTime();
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
			dao.updateSchedule(schedule.getScheduleId(), schedule);
			Animal animal = new Animal();
			animal.setFeedingSchedule(schedule.getScheduleId());
			assert expected == dao.getSchedule(animal).getFeedingTime();
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
			dao.updateSchedule(schedule.getScheduleId(), schedule);
			Animal animal = new Animal();
			animal.setFeedingSchedule(schedule.getScheduleId());
			assert expected == dao.getSchedule(animal).getFeedingTime();
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
	   test.currentSchedules.add(test1);
	   test.getAllSchedulesTest();
	   
	   test.addScheduleTest(test2);
	   test.getAllSchedulesTest();

	   test.removeScheduleTest(test2);
	   test.getAllSchedulesTest();
	   
	   test.addScheduleTest(test3);
	   test.addScheduleTest(test4);
	   test.getAllSchedulesTest();
	   
	   test.getScheduleTest(animal, test1);
	   
	   test.updateScheduleFoodTest(test4, "new food");
	   test.updateScheduleNotesTest(test4, "new note");
	   test.updateScheduleRecurrenceTest(test4, "new recurrence");
	   test.updateScheduleTimeTest(test4, "new time");
	   
	}
}
