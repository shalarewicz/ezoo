package shala.ezoo.dao;

import java.util.List;

import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO {
	
    /**
     * @param id
     * @return Schedule specified by the id
     */
    public FeedingSchedule getScheduleByID(long id);
    /**
      * Adds the given schedule to the database
      * @param schedule
      * @throws Exception if a constraint violation occurs
      */
     public void saveSchedule(FeedingSchedule schedule) throws Exception;
     /**
      * Updates the given schedule in the database. Adds the schedule if not already present.
      * @param schedule
      */
     public void updateSchedule(FeedingSchedule schedule);
     
     /**
      * Removes the given schedule from the database. Removes all references to the
      * schedule from the database.
      * @param scheduleId
      */
     public void removeSchedule(long scheduleId);
     
     /**
      * Obtains all schedules in the database.
      * @return A set of Schedules. 
      */
     public List<FeedingSchedule> getAllSchedules();
     
     /**
      * Sets the schedule for the given animal. Adds the schedule and animal to the 
      * db if not present
      * @param schedule new schedule. Cannot be null. Overwrites existing schedule Id
      * @param animal animal receiving the schedule Cannot be null.
      */
     public void setFeedingSchedule(FeedingSchedule schedule, Animal animal);
     
     /**
      * Sets the schedule for the given animal. No action if schedule or animal not present in db
      * @param schedule id of schedule being assigned to the animal
      * @param animal id of animal receiving the schedule
      */
     public void setFeedingSchedule(long schedule, long animal);

}
