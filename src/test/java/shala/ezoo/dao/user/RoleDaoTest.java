package shala.ezoo.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shala.ezoo.config.HibernateConfig;
import shala.ezoo.model.Role;
import shala.ezoo.model.User;
import shala.ezoo.model.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class, HibernateRoleDaoImpl.class, HibernateUserDaoImpl.class})
public class RoleDaoTest {
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired 
    private UserDao userDao;
    
    
    private static Role role1;
    private static Role role2;
    private static User user1;
    
    @Before
    public void setup() {
        role1 = new Role(UserRole.USER);
        role2 = new Role(UserRole.ADMIN);
        user1 = new User("user1", "first1", "last1", "e mail1", "phone1");
    }
    
    
    @After
    public void teardown() {
        roleDao.removeRole(UserRole.USER);
        roleDao.removeRole(UserRole.ADMIN);
        userDao.removeUser(user1.getUsername());
    }
    
    @Test
    public void testSaveandGet() {
        assertTrue(roleDao.saveRole(role1));
        assertEquals(role1, roleDao.getRole(UserRole.toString(role1.getName())));
    }
  
    @Test 
    public void testRemoveRole() {
        roleDao.saveRole(role1);
        roleDao.removeRole(UserRole.USER);
        assertNull(roleDao.getRole(UserRole.toString(role1.getName())));
    }
    
    @Test
    public void testRemoveAssignedRole() {
        user1.addRole(role1);
        userDao.saveUser(user1);

        assertTrue(roleDao.getAllRoles(user1.getUsername()).contains(role1));
        assertFalse(roleDao.getAllRoles(user1.getUsername()).isEmpty());
        assertNotNull(userDao.getUser(user1.getUsername()));
        
    }
    
    @Test
    public void testSaveRoleThroughUser() {
        user1.addRole(role1);
        userDao.saveUser(user1);
        assertEquals(role1, roleDao.getRole(UserRole.toString(role1.getName())));
    }
    
    
    @Test
    public void testGetAllRoles() {
        roleDao.saveRole(role1);
        roleDao.saveRole(role2);
        List<Role> roles = roleDao.getAllRoles();
        assertTrue(roles.size() == 2);
        assertTrue(roles.contains(role1));
        assertTrue(roles.contains(role2));
    }
    

}