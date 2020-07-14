package shala.ezoo.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shala.ezoo.config.HibernateConfig;
import shala.ezoo.model.User;
import shala.ezoo.model.UserRole;

//@ExtendWith(SpringExten.class) TODO For JUnit 5 (requires Spring 5)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class, HibernateUserDaoImpl.class, HibernateRoleDaoImpl.class})
//        loader=AnnotationConfigContextLoader.class)
public class UserDaoTest {
    //    @Configuration
    //    static class ContextConfiguration {
    //        Bean configurations
    //    }
        
    @Autowired
    private UserDao dao;
    
    @Autowired 
    private RoleDao roleDao;
    
    private static User user1;
    private static User user2;
    private static User user3;
    
    @Before
    public void setup() {
        user1 = new User("user1", "first1", "last1", "e mail1", "phone1");
        user2 = new User("user2", "first2", "last2", "email2", "phone2");
        user3 = new User("user3", "first3", "last3", "email3", "phone3");
        user1.setPassword("password1");
        user2.setPassword("password2");
        user3.setPassword("password3");
        dao.saveUser(user1);
        dao.saveUser(user2);
    }
    
    @After
    public void teardown() {
        dao.removeUser("user1");
        dao.removeUser("user2");
        dao.removeUser("user3");
    }
    
    private static void checkUserDetailsEqual(User expected, User actual) {
        assertEquals("firstName not equal", expected.getFirstName(), actual.getFirstName());
        assertEquals("lastName not equal", expected.getLastName(), actual.getLastName());
        assertEquals("email not equal", expected.getEmail(), actual.getEmail());
        assertEquals("phone not equal", expected.getPhone(), actual.getPhone());
        assertEquals("password not equal", expected.getPassword(), actual.getPassword());
    }
    
    @Test
    public void testGetUser() {
        dao.saveUser(user1);
        User actual = dao.getUser(user1.getUsername());
        assertEquals("usernames not equal", user1, actual);
        checkUserDetailsEqual(user1, actual);
    }
    
    @Test
    public void testGetNullUser() {
        assertNull("User should not be persisted to db", dao.getUser("user5"));
    }
    
    @Test
    public void testSave() {
       assertTrue(dao.saveUser(user3));
       User savedUser = dao.getUser(user3.getUsername());
       assertEquals(user3, savedUser);
       checkUserDetailsEqual(user3, savedUser);
    }
    
    @Test
    public void testSaveToEmptyDB() {
      teardown();
      testSave();
    }
    
    @Test
    public void testSaveDuplicate() {
        assertFalse(dao.saveUser(user1));
    }
    
    private void testUpdate(User updated) {
        dao.updateUser(updated);
        User actual = dao.getUser(updated.getUsername());
        assertEquals(updated, actual);
        checkUserDetailsEqual(updated, actual);
    }
    
    @Test
    public void testUpdateUserFirstName() {
        user1.setFirstName("newFirst");
        testUpdate(user1);
    }
    
    @Test
    public void testUpdateUserLastName() {
        user1.setLastName("newLast");
        testUpdate(user1);
    }
    
    @Test
    public void testUpdateUserUsername() {
        String oldUsername = user1.getUsername();
        user1.setUsername("newUsername");
        testUpdate(user1);
        assertNotNull(dao.getUser(oldUsername));
        
    }
    
    @Test
    public void testUpdateUserEmail() {
        user1.setEmail("new email");
        testUpdate(user1);
    }
    
    @Test
    public void testUpdateUserPassword() {
        user1.setPassword("new password");
        testUpdate(user1);
    }
    
    @Test
    public void testUpdateUserPhone() {
        user1.setPhone("new phone");
        testUpdate(user1);
    }
    
    @Test
    public void testRemoveUser() {
        User user = dao.removeUser(user1.getUsername());
        assertNull(dao.getUser(user1.getUsername()));
        assertEquals(user1, user);
        checkUserDetailsEqual(user1, user);
    }
    
    @Test
    public void testRemoveNonexistentUser() {
        assertNull(dao.removeUser(user3.getUsername()));
    }
    
    @Test
    public void testExistsEmail() {
        assertTrue(dao.existsEmail(user1.getEmail()));
        assertFalse(dao.existsEmail(user3.getEmail()));
    }
    
    @Test
    public void testExistsUsername() {
        assertTrue(dao.existsUsername(user1.getUsername()));
        assertFalse(dao.existsUsername(user3.getUsername()));
    }
    
    @Test
    public void testAssignRole() {
        dao.assignRole(user2.getUsername(), UserRole.USER);
        assertNotNull(roleDao.getRole(UserRole.toString(UserRole.USER)));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAssignRoletoBadUser() {
        dao.assignRole("badUsername", UserRole.USER);
    }
    
    
}
