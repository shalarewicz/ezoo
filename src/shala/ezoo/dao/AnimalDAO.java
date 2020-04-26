package shala.ezoo.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

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
	public List<Animal> getAllAnimals();
	
	
	/**
	 * Used to retrieve a list of all Animals with the given feeding schedule
	 * @param scheduleId feeding schedule ID
	 * @return List of Animals with the given feeding schedule
	 */
	public List<Animal> getAllAnimals(long scheduleId);

	/**
	 * Used to persist the animal to the datastore
	 * @param animal
	 */
	public boolean saveAnimal(Animal animal) throws DataIntegrityViolationException;
	
	/**
	 * Removes an animal from the database
	 * @param animalId id of animal being removed. 
	 */
	public Animal removeAnimal(long animalId);

	
	/**
	 * Updates the Animal in the datastore
	 * @param animal
	 */
	public void updateAnimal(Animal animal);
	
	/**
     * Removes the schedule for the provided animal
     * @param animalId
     */
    public boolean removeSchedule(long animalId);
}
