package shala.ezoo.dao.user;

import org.springframework.dao.DataIntegrityViolationException;

import shala.ezoo.model.Role;
import shala.ezoo.model.User;

public interface UserDao {
    
    /**
     * Obtain the user associated with the given username
     * @param username
     * @return the specified user
     */
    public User getUser(String username);
    
    /**
     * Saves a new user to the database
     * @param user new user. 
     * @return True if save successful
     * @throws DataIntegrityViolationException if datastore constraint violation occurs
     */
    public boolean saveUser(User user) throws DataIntegrityViolationException;
    
    /**
     * Updates the datastore user matching the provided user's username
     * @param user
     */
    public void updateUser(User user);
    
    /**
     * Removes a user from the datastore
     * @param username Username of the user to be removed. 
     * @return the removed user
     */
    public User removeUser(String username);
    
    
    /**
     * Checks to see if the given email address exists in the datastore
     * @param email
     * @return True if the email exists
     */
    public boolean existsEmail(String email);
    
    /**
     * Checks to see if the given username exists in the datastore
     * @param username
     * @return True if the email exists
     */
    public boolean existsUsername(String username);
    
    
    /**
     * Assigns the specified role to the user. Persists role to the database if not present. 
     * @param username
     * @param userRole
     * @throws IllegalArgumentException if username is not present
     */
    public void assignRole(String username, Role userRole) throws IllegalArgumentException;
}
