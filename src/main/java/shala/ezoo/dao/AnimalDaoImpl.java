package shala.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import shala.ezoo.exceptions.DatabaseConstraintViolationException;
import shala.ezoo.model.Animal;

public class AnimalDaoImpl implements AnimalDAO {
    
    private static Animal getAnimal(ResultSet rs, FeedingScheduleDAO fsDao) {
        Animal a = new Animal();

        try {
            a.setAnimalID(rs.getLong("animalid"));
            a.setName(rs.getString("name"));
    
            a.setTaxKingdom(rs.getString("taxkingdom"));
            a.setTaxPhylum(rs.getString("taxphylum"));
            a.setTaxClass(rs.getString("taxclass"));
            a.setTaxOrder(rs.getString("taxorder"));
            a.setTaxFamily(rs.getString("taxfamily"));
            a.setTaxGenus(rs.getString("taxgenus"));
            a.setTaxSpecies(rs.getString("taxspecies"));
            
            a.setHeight(rs.getDouble("height"));
            a.setWeight(rs.getDouble("weight"));
    
            a.setType(rs.getString("type"));
            a.setHealthStatus(rs.getString("healthstatus"));
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
            return a;
        
    }
	
	private static List<Animal> getAnimals(String sql) {
		
		List<Animal> animals = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			FeedingScheduleDAO fsDao = new FeedingScheduleDAOImpl();

			while (rs.next()) {
				animals.add(getAnimal(rs, fsDao));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return animals;
	}
	
	@Override
	public List<Animal> getAllAnimals(long scheduleId) {
		return getAnimals("SELECT * FROM ANIMALS WHERE feedingschedule = " + scheduleId + ";");
	}
	
	@Override
	public List<Animal> getAllAnimals() {
		return getAnimals("SELECT * FROM ANIMALS");
	}

	@Override
	public boolean saveAnimal(Animal animal) throws DatabaseConstraintViolationException {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO ANIMALS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);

			// Add parameters from animal into PreparedStatement
			stmt.setLong(1, animal.getAnimalID());
			stmt.setString(2, animal.getName());

			stmt.setString(3, animal.getTaxKingdom());
			stmt.setString(4, animal.getTaxPhylum());
			stmt.setString(5, animal.getTaxClass());
			stmt.setString(6, animal.getTaxOrder());
			stmt.setString(7, animal.getTaxFamily());
			stmt.setString(8, animal.getTaxGenus());
			stmt.setString(9, animal.getTaxSpecies());

			stmt.setDouble(10, animal.getHeight());
			stmt.setDouble(11, animal.getWeight());

			stmt.setString(12, animal.getType());
			stmt.setString(13, animal.getHealthStatus());
			stmt.setObject(14, animal.getFeedingSchedule()); // Use set object for when feedingSchedule null
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseConstraintViolationException("Failed to save animal: " + animal.getAnimalID(), e);
		} finally {
			try {
			    if (success == 1) {
    				if (stmt != null)
    					stmt.close();
    				if (connection != null)
    					connection.close();
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return success == 1;
	}
	
	@Override
	public Animal removeAnimal(long id) {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		Animal deleted = null;
		
		try {
		    deleted = getAnimalByID(id);
			connection = DAOUtilities.getConnection();
			
			String delete = "DELETE FROM ANIMALS WHERE animalid = (?)";
			
			stmt = connection.prepareStatement(delete);
			
			stmt.setLong(1, id);
			
			success = stmt.executeUpdate();
				
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
		
		return (success == 1) ? deleted : null;
		
	}
	
	@Override
	public void updateAnimal(Animal animal) {
		Connection connection = null;
		PreparedStatement stmt = null;
		long id = animal.getAnimalID();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ANIMALS SET name = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET taxKingdom = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET taxPhylum = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET taxClass = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET taxOrder = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET taxFamily = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET taxGenus = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET taxSpecies = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET height = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET weight = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET type = (?) WHERE animalid = (?);" +
					     "UPDATE ANIMALS SET healthStatus = (?) WHERE animalid = (?);" +
						 "UPDATE ANIMALS SET feedingschedule = (?) WHERE animalid = (?);";
					
			stmt = connection.prepareStatement(sql);
			
			
			stmt.setLong(2, animal.getAnimalID());
			stmt.setLong(4, id);
			stmt.setLong(6, id);
			stmt.setLong(8, id);
			stmt.setLong(10, id);
			stmt.setLong(12, id);
			stmt.setLong(14, id);
			stmt.setLong(16, id);
			stmt.setLong(18, id);
			stmt.setLong(20, id);
			stmt.setLong(22, id);
			stmt.setLong(24, id);
			stmt.setLong(26, id);
			stmt.setString(1, animal.getName());
			stmt.setString(3, animal.getTaxKingdom());
			stmt.setString(5, animal.getTaxPhylum());
			stmt.setString(7, animal.getTaxClass());
			stmt.setString(9, animal.getTaxOrder());
			stmt.setString(11, animal.getTaxFamily());
			stmt.setString(13, animal.getTaxGenus());
			stmt.setString(15, animal.getTaxSpecies());
			stmt.setDouble(17, animal.getHeight());
			stmt.setDouble(19, animal.getWeight());
			stmt.setString(21, animal.getType());
			stmt.setString(23, animal.getHealthStatus());
			stmt.setLong(25, animal.getFeedingSchedule().getScheduleId());
			
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
    public Animal getAnimalByID(long id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            connection = DAOUtilities.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM ANIMALS WHERE animalId = (?);");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return getAnimal(rs, new FeedingScheduleDAOImpl());
            
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
        
        return null; // TODO Throw Exception? nulls are bad
    }

    public boolean removeSchedule(long animalId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;
        
        try {
            connection = DAOUtilities.getConnection();
            String sql = "UPDATE ANIMALS SET feedingschedule = (?) WHERE animalid = (?);";
            stmt = connection.prepareStatement(sql);
            stmt.setObject(1, null);
            stmt.setLong(2, animalId);
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
        
        return success ==1;
        
    }

}
