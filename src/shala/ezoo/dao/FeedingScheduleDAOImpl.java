package shala.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;

public class FeedingScheduleDAOImpl implements FeedingScheduleDAO {
    
    private static FeedingSchedule getSchedule(ResultSet rs, AnimalDAO dao) {
        FeedingSchedule fs = new FeedingSchedule();
        
        try {
            fs.setScheduleId(rs.getLong("scheduleid"));
            fs.setFeedingTime(rs.getString("feeding_time"));
            fs.setRecurrence(rs.getString("recurrence"));
            fs.setFood(rs.getString("food"));
            fs.setNotes(rs.getString("notes"));
            fs.addAnimals(dao.getAllAnimals(fs.getScheduleId()));
        
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fs;
    }

	@Override
	public void saveSchedule(FeedingSchedule schedule) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO FEEDING_SCHEDULES VALUES (?, ?, ?, ?, ?)";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, schedule.getScheduleId());
			stmt.setString(2, schedule.getFeedingTime().toString());
			stmt.setString(3, schedule.getRecurrence());
			stmt.setString(4, schedule.getFood());
			stmt.setString(5, schedule.getNotes());
			
			stmt.executeUpdate();
			
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
	}

	@Override
	public void removeSchedule(long id) {
		
		Connection connection = null;
		PreparedStatement animalStmt = null;
		PreparedStatement feedingStmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			
			
			// Remove all references to the schedule in animals
			String animalsUpdate = "UPDATE ANIMALS SET feedingschedule = null WHERE feedingschedule = (?)";
			
			animalStmt = connection.prepareStatement(animalsUpdate);
			
			animalStmt.setLong(1, id);
			
			success = animalStmt.executeUpdate();
			
			// Remove the schedule from FEEDING_SCHEDULES
			
			String delete = "DELETE FROM FEEDING_SCHEDULES WHERE scheduleid = (?)";
			
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
		
	}

	@Override
	public List<FeedingSchedule> getAllSchedules() {
		List<FeedingSchedule> result = new ArrayList<FeedingSchedule>();
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			stmt = connection.createStatement();
			
			String sql = "SELECT * FROM FEEDING_SCHEDULES";
			
			ResultSet rs = stmt.executeQuery(sql);
			AnimalDAO dao = DAOUtilities.getAnimalDao();
			
			while (rs.next()) {
				
				FeedingSchedule fs = new FeedingSchedule();
			
				fs.setScheduleId(rs.getLong("scheduleid"));
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
	public void setFeedingSchedule(FeedingSchedule schedule, Animal animal) {
		// Update animal and add to Feeding_Schedules if schedule does not exist
		
		saveSchedule(schedule); 
		this.setFeedingSchedule(schedule.getScheduleId(), animal.getAnimalID());
	}

	@Override
	public void setFeedingSchedule(long scheduleId, long animalId) {
		// Update animal and add to Feeding_Schedules if schedule does not exist
		
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DAOUtilities.getConnection();
			
			String sql = "UPDATE ANIMALS SET feeding_schedule = (?) WHERE animalid = (?)";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1,  scheduleId);
			stmt.setLong(2,  animalId);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Use NotFoundException to prevent other SQL errors giving this error
//			throw new SQLException("Animal and Schedule must be added before assignemt");
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
		
	}


	@Override
	public void updateSchedule(FeedingSchedule schedule) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE FEEDING_SCHEDULES SET feeding_time = (?) WHERE scheduleid = (?);" +
						 "UPDATE FEEDING_SCHEDULES SET recurrence = (?) WHERE scheduleid = (?); "  +
						 "UPDATE FEEDING_SCHEDULES SET food = (?) WHERE scheduleid = (?); " +
						 "UPDATE FEEDING_SCHEDULES SET notes = (?) WHERE scheduleid = (?); ";
					
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(2, schedule.getScheduleId());
			stmt.setLong(4, schedule.getScheduleId());
			stmt.setLong(6, schedule.getScheduleId());
			stmt.setLong(8, schedule.getScheduleId());
			stmt.setString(1, schedule.getFeedingTime().toString());
			stmt.setString(3, schedule.getRecurrence());
			stmt.setString(5, schedule.getFood());
			stmt.setString(7, schedule.getNotes());
			
			stmt.executeUpdate();
			
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
	}

    public FeedingSchedule getScheduleByID(long id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            connection = DAOUtilities.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM FEEDING_SCHEDULES WHERE scheduleId = (?);");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return getSchedule(rs, new AnimalDaoImpl());
            
        } catch(SQLException sqe) {
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
        
        return null; 
    }

}
