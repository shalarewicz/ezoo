package shala.ezoo.dao.user;

import shala.ezoo.model.Role;

public interface RoleDao {
    
    /**
     * Retrieves the given role from the database
     * @param name name of role being retrieved
     * @return Role with the given name
     */
    public Role getRole(String name);
    
    /**
     * Saves the role to the database. Overwrites any existing records. 
     * @param role
     * @return true if save was successful
     */
    public boolean saveRole(Role role);

    
    public void removeRole(Role role);
}
