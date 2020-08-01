package shala.ezoo.dao.user;

import java.util.List;

import shala.ezoo.model.Role;
import shala.ezoo.model.UserRole;

public interface RoleDao {
    
    /**
     * Retrieves the given role from the database
     * @param name name of role being retrieved
     * @return Role with the given name
     */
    public Role getRole(String name);
    
    /**
     * Returns a list of roles for the specified user. 
     * @param username
     * @return roles which the user has been granted. 
     */
    public List<Role> getAllRoles(String username);
    
    /**
     * Returns a list of roles
     * @return a list of all roles
     */
    public List<Role> getAllRoles();
    
    /**
     * Saves the role to the database. Overwrites any existing records. 
     * @param role
     * @return true if save was successful
     */
    public boolean saveRole(Role role);

    /**
     * Removes the specified role from the database
     * @param role
     */
    public void removeRole(UserRole role);
    

}
