package shala.ezoo.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import shala.ezoo.exceptions.DatabaseConstraintViolationException;
import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO {
	
    /**
     * @param id
     * @return Schedule specified by the id or null if schedule does not persist in the database
     */
    public FeedingSchedule getScheduleByID(long id);
    
    /**
     * Adds the given schedule to the database
     * @param schedule
     * @return True if the save was successful
     * @throws DataIntegrityViolationException if a constraint violation occurs
     */
    public boolean saveSchedule(FeedingSchedule schedule) throws DataIntegrityViolationException;
     
    /**
     * Updates the given schedule in the database. Adds the schedule if not already present.
     * @param schedule
     */
    public void updateSchedule(FeedingSchedule schedule);
    
    /**
     * Removes the given schedule from the database. Removes all references to the
     * schedule from the database.
     * @param scheduleId
     * @return the removed Feeding Schedule. 
     */
    public FeedingSchedule removeSchedule(long scheduleId);
     
    /**
     * Obtains all schedules in the database.
     * @return A set of Schedules. 
     */
    public List<FeedingSchedule> getAllSchedules();
     
    /**
     * Sets the schedule for the given animal. Adds the schedule and animal to the 
     * datastore if not present
     * @param schedule new schedule. Cannot be null. Overwrites existing schedule Id
     * @param animal animal receiving the schedule Cannot be null.
     * @return true if schedule successfully set
     */
    public boolean setFeedingSchedule(FeedingSchedule schedule, Animal animal) throws DataIntegrityViolationException;
     
    /**
     * Sets the schedule for the given animal. No action if schedule or animal not present in datastore
     * @param schedule id of schedule being assigned to the animal
     * @param animal id of animal receiving the schedule
     * @return true if schedule successfully set
     */
    public boolean setFeedingSchedule(long schedule, long animal) throws DatabaseConstraintViolationException;

}
