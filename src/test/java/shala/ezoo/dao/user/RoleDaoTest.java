package shala.ezoo.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shala.ezoo.config.HibernateConfig;
import shala.ezoo.model.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class, HibernateRoleDaoImpl.class, HibernateUserDaoImpl.class})
public class RoleDaoTest {
    
    @Autowired
    private RoleDao dao;
    
    @Autowired
    
    private static final Role role1 = new Role("ROLE_USER");
    
    @After
    public void teardown() {
        dao.removeRole(role1);
//        dao.removeRole(role2);
    }
    
    @Test
    public void testSaveandGet() {
        assertTrue(dao.saveRole(role1));
        assertEquals(role1, dao.getRole(role1.getName()));
    }
  
    @Test 
    public void testRemoveRole() {
        dao.saveRole(role1);
        dao.removeRole(role1);
        assertNull(dao.getRole(role1.getName()));
    }
}