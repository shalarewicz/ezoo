package shala.ezoo.dao;

import java.sql.SQLException;
import java.util.List;

import shala.ezoo.model.Animal;

/**
 * Main interface used to execute CRUD methods on Animal class
 * @author anon
 *
 */
public interface AnimalDAO {

    /**
     * @param id
     * @return Animal with the specified ID
     */
    public Animal getAnimalByID(long id);
    
	/**
	 * @return List of all Animals
	 */
	List<Animal> getAllAnimals();
	
	
	/**
	 * Used to retrieve a list of all Animals with the given feeding schedule
	 * @param scheduleId feeding schedule ID
	 * @return List of Animals with the given feeding schedule
	 */
	List<Animal> getAllAnimals(long scheduleId);

	/**
	 * Used to persist the animal to the datastore
	 * @param animalToSave
	 */
	void saveAnimal(Animal animalToSave) throws SQLException;
	
	/**
	 * Removes an animal from the database
	 * @param animalId id of animal being removed. 
	 */
	void removeAnimal(long animalId);

	
	/**
	 * Updates the Animal in the datastore
	 * @param animal
	 */
	void updateAnimal(Animal animal);
	
	/**
     * Removes the schedule for the provided animal
     * @param animalId
     */
    public void removeSchedule(long animalId);
}
