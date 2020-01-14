package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;

public class AnimalDaoImpl implements AnimalDAO {

	@Override
	public List<Animal> getAllAnimals() {
		List<Animal> animals = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT * FROM ANIMALS";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Animal a = new Animal();

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
				
				animals.add(a);
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
	public void saveAnimal(Animal animal) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO ANIMALS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

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

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Insert animal failed: " + animal);
		}

	}

}
