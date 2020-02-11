package com.examples.ezoo.dao;

import java.sql.SQLException;
import java.util.Set;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO {
	
	/**
	 * Adds the given schedule to the database
	 * @param schedule
	 * @return True if schedule added successfully
	 */
	public boolean addSchedule(FeedingSchedule schedule) throws Exception;
	
	/**
	 * Updates the given schedule to the database
	 * @param id id of schedule being updated
	 * @param schedule
	 * @return True if schedule added successfully
	 */
	public boolean updateSchedule(long id, FeedingSchedule schedule) throws Exception;
	
	/**
	 * Removes the given schedule from the database. Removes all references to the
	 * schedule from the database.
	 * @param scheduleId
	 * @return False if the schedule was not removed or did not exist.
	 */
	public boolean removeSchedule(long scheduleId);
	
	/**
	 * Obtains all schedules in the database. Without duplicates
	 * @return A set of Schedules. 
	 */
	public Set<FeedingSchedule> getAllSchedules();
	
	/**
	 * Obtains the schedule for the given animal
	 * @param animal
	 * @return
	 */
	public FeedingSchedule getSchedule(Animal animal);
	
	/**
	 * Sets the schedule for the given animal
	 * @param schedule new schedule
	 * @param animal animal receiving the schedule
	 * @return False if animal did not exist
	 * @throws Exception if Animal does not exist
	 * @throws SQLException if Animal or FeedingSchedule does not exist
	 */
	public boolean setFeedingSchedule(FeedingSchedule schedule, Animal animal) throws Exception, SQLException;
	
	/**
	 * Sets the schedule for the given animal
	 * @param schedule id of schedule being assigned to the animal
	 * @param animal id of animal receiving the schedule
	 * @return False if operation fails
	 * @throws SQLException if Animal or FeedingSchedule does not exist
	 */
	public boolean setFeedingSchedule(long schedule, long animal) throws SQLException;
	
	/**
	 * Removes the schedule for the provided animal
	 * @param animal
	 * @return False if removal failed
	 */
	public boolean removeSchedule(Animal animal);

}
