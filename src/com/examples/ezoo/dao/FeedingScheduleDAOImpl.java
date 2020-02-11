package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDAOImpl implements FeedingScheduleDAO {

	@Override
	public boolean addSchedule(FeedingSchedule schedule) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO \"FEEDING_SCHEDULES\" VALUES (?, ?, ?, ?, ?)";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, schedule.getScheduleId());
			stmt.setString(2, schedule.getFeedingTime().toString());
			stmt.setString(3, schedule.getRecurrence());
			stmt.setString(4, schedule.getFood());
			stmt.setString(5, schedule.getNotes());
			
			success = stmt.executeUpdate();
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return success == 1;
	}

	@Override
	public boolean removeSchedule(long id) {
		
		Connection connection = null;
		PreparedStatement animalStmt = null;
		PreparedStatement feedingStmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			
			
			// Remove all references to the schedule in animals
			String animalsUpdate = "UPDATE ANIMALS SET feeding_schedule = null WHERE feeding_schedule = (?)";
			
			animalStmt = connection.prepareStatement(animalsUpdate);
			
			animalStmt.setLong(1, id);
			
			success = animalStmt.executeUpdate();
			
			// Remove the schedule from FEEDING_SCHEDULES
			
			String delete = "DELETE FROM \"FEEDING_SCHEDULES\" WHERE schedule_id = (?)";
			
			feedingStmt = connection.prepareStatement(delete);
			
			feedingStmt.setLong(1, id);
			
			success = (success == feedingStmt.executeUpdate()) ? 1 : 0;
				
				
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				if (animalStmt != null) {
					animalStmt.close();
				}
				if (feedingStmt != null) {
					feedingStmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		
		return success == 1;
	}

	@Override
	public Set<FeedingSchedule> getAllSchedules() {
		Set<FeedingSchedule> result = new HashSet<FeedingSchedule>();
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			stmt = connection.createStatement();
			
			String sql = "SELECT * FROM \"FEEDING_SCHEDULES\"";
			
			ResultSet rs = stmt.executeQuery(sql);
			AnimalDAO dao = DAOUtilities.getAnimalDao();
			
			while (rs.next()) {
				
				FeedingSchedule fs = new FeedingSchedule();
			
				fs.setScheduleId(rs.getLong("schedule_id"));
				fs.setFeedingTime(rs.getString("feeding_time"));
				fs.setRecurrence(rs.getString("recurrence"));
				fs.setFood(rs.getString("food"));
				fs.setNotes(rs.getString("notes"));
				fs.addAnimals(dao.getAllAnimals(fs.getScheduleId()));
				
				result.add(fs);
			}
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public FeedingSchedule getSchedule(Animal animal) {
		FeedingSchedule fs = new FeedingSchedule();
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "SELECT * FROM \"FEEDING_SCHEDULES\" WHERE schedule_id = "
					+ "(SELECT feeding_schedule FROM ANIMALS WHERE animalid = (?))";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, animal.getAnimalID());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				fs.setScheduleId(rs.getLong("schedule_id"));
				fs.setFeedingTime(rs.getString("feeding_time"));
				fs.setRecurrence(rs.getString("recurrence"));
				fs.setFood(rs.getString("food"));
				fs.setNotes(rs.getString("notes"));
				
				return fs;
			}
				
				
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		
		return null; // Schedule does not exist TODO Exception or EmptySchedule
	}

	@Override
	public boolean setFeedingSchedule(FeedingSchedule schedule, Animal animal) throws Exception, SQLException {
		// Update animal and add to Feeding_Schedules if schedule does not exist
		
		addSchedule(schedule); 
		animal.setFeedingSchedule(schedule.getScheduleId());
		
		return this.setFeedingSchedule(schedule.getScheduleId(), animal.getAnimalID());
	}

	@Override
	public boolean setFeedingSchedule(long scheduleId, long animalId) throws SQLException {
		// Update animal and add to Feeding_Schedules if schedule does not exist
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "UPDATE ANIMALS SET feeding_schedule = (?) WHERE animalid = (?)";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1,  scheduleId);
			stmt.setLong(2,  animalId);
			
			success = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Use NotFoundException to prevent other SQL errors giving this error
			throw new SQLException("Animal and Schedule must be added before assignemt");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return success == 1; // Exception instead? 
	}

	@Override
	public boolean removeSchedule(Animal animal) {

		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "UPDATE ANIMALS SET feeding_schedule = null WHERE animalid = (?)";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, animal.getAnimalID());
			
			success = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return success == 1;
	}

	@Override
	public boolean updateSchedule(long id, FeedingSchedule schedule) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE \"FEEDING_SCHEDULES\" SET feeding_time = (?) WHERE schedule_id = (?);" +
						 "UPDATE \"FEEDING_SCHEDULES\" SET recurrence = (?) WHERE schedule_id = (?); "  +
						 "UPDATE \"FEEDING_SCHEDULES\" SET food = (?) WHERE schedule_id = (?); " +
						 "UPDATE \"FEEDING_SCHEDULES\" SET notes = (?) WHERE schedule_id = (?); ";
					
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(2, schedule.getScheduleId());
			stmt.setLong(4, schedule.getScheduleId());
			stmt.setLong(6, schedule.getScheduleId());
			stmt.setLong(8, schedule.getScheduleId());
			stmt.setString(1, schedule.getFeedingTime().toString());
			stmt.setString(3, schedule.getRecurrence());
			stmt.setString(5, schedule.getFood());
			stmt.setString(7, schedule.getNotes());
			
			success = stmt.executeUpdate();
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return success == 1;
	}

}
