package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;

/**
 * Main interface used to execute CRUD methods on Animal class
 * @author anon
 *
 */
public interface AnimalDAO {

	/**
	 * Used to retrieve a list of all Animals 
	 * @return
	 */
	List<Animal> getAllAnimals();
	
	
	/**
	 * Used to retrieve a list of all Animals with the given feeding schedule
	 * @param scheduleId feeding schedule ID
	 * @return
	 */
	List<Animal> getAllAnimals(long scheduleId);

	/**
	 * Used to persist the animal to the datastore
	 * @param animalToSave
	 */
	void saveAnimal(Animal animalToSave) throws Exception;
	
	/**
	 * Removes an animal from the database
	 * @param animalId id of animal being removed. 
	 */
	void removeAnimal(long animalId) throws Exception;


	boolean updateAnimal(long id, Animal animal) throws Exception;
}
