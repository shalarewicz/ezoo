package shala.ezoo.dao;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shala.ezoo.config.HibernateConfig;
import shala.ezoo.dao.user.HibernateUserDaoImpl;
import shala.ezoo.dao.user.UserDao;
import shala.ezoo.model.Event;
import shala.ezoo.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class, HibernateEventDaoImpl.class, HibernateUserDaoImpl.class})
public class EventDaoTest {
    
    @Autowired
    private EventDao dao;
    
    @Autowired
    private UserDao userDao;
    
    private static User user1;
    private static User user2;
    
    private long eventId1;
    private long eventId2;
    private long eventId3;
    
    private static Event event1;
    private static Event event2;
    private static Event event3;
    

    @Before
    public void setup() {
        user1 = new User("user1", "first1", "last1", "e mail1", "phone1");
        user2 = new User("user2", "first2", "last2", "email2", "phone2");
        userDao.saveUser(user1);
        userDao.saveUser(user2);

        event1 = new Event("name1", "creator1", LocalDateTime.now(), "location1", "description1", new ArrayList<User>());
        event2 = new Event("name2", "creator2", LocalDateTime.now(), "location2", "description2", new ArrayList<User>());
        event3 = new Event("name3", "creator3", LocalDateTime.now(), "location3", "description3", new ArrayList<User>());
        eventId1 = dao.saveEvent(event1);
        eventId2 = dao.saveEvent(event2);
        event1.setEventId(eventId1);
        event2.setEventId(eventId2);
    }
    
    @After
    public void teardown() {
        dao.removeEvent(eventId1);
        dao.removeEvent(eventId2);
        dao.removeEvent(eventId3);
        
        userDao.removeUser(user1.getUsername());
        userDao.removeUser(user2.getUsername());
    }
    
    @Test
    public void testGetEvent() {
        Event actual = dao.getEvent(eventId1);
        assertEquals(event1, actual);
    }
    
    @Test 
    public void testGetNullEvent() {
        assertNull(dao.getEvent(-1L));
    }
    
    @Test
    public void testSaveEvent(){
        eventId3 = dao.saveEvent(event3);
        event3.setEventId(eventId3);
        assertEquals(event3, dao.getEvent(eventId3));
    }
    
    @Test
    public void testDuplicateSave() {
        List<Event> prior = dao.getAllEvents();
        assertEquals(0L, dao.saveEvent(event1));
        assertEquals(prior, dao.getAllEvents());
    }
    
    @Test
    public void testSaveToEmptyDB() {
        teardown();
        testSaveEvent();
    }
    
    @Test
    public void testRemoveEvent() {
        List<Event> prior = dao.getAllEvents();
        int priorSize = prior.size();
        prior.remove(event1);
        Event removed = dao.removeEvent(eventId1);
        List<Event> after = dao.getAllEvents();
        assertEquals(prior, after);
        assertEquals(priorSize - 1, after.size());
        assertEquals(event1, removed);
    }
    
    @Test
    public void testRemoveNonExistentEvent() {
        assertNull(dao.removeEvent(eventId3));
    }
    
    public void testUpdate(Event updated) {
        dao.updateEvent(event1);
        assertEquals(updated, dao.getEvent(updated.getEventId()));
    }
    
    @Test
    public void testUpdateLocation() {
        event1.setLocation("new location");
        testUpdate(event1);
    }
    
    @Test
    public void testUpdateTime() {
        event1.setTime(LocalDateTime.of(2020, 07, 31, 5, 01));
        testUpdate(event1);
    }
    
    @Test
    public void testUpdateName() {
        event1.setName("new name");
        testUpdate(event1);
    }
    
    @Test
    public void testUpdateDescription() {
        event1.setName("new desc");
        testUpdate(event1);
    }
    
    @Test
    public void testAttendees() {
        event1.addAttendee(user1);
        dao.updateEvent(event1);
        Event actual = dao.getEvent(eventId1);
        assertEquals(event1.getAttendees().size(), actual.getAttendees().size());
        assertEquals(new ArrayList(event1.getAttendees()), new ArrayList(actual.getAttendees()));
        
        testUpdate(event1);
    }
    
    @Test
    public void testRegisterUser() {
        List<Event> prior = dao.getEventsAttended(user1.getUsername());
        dao.registerEventUser(eventId1, user1.getUsername());
        List<Event> after = dao.getEventsAttended(user1.getUsername());
        assertNotNull(dao.getEventsAttended(user1.getUsername()));
        assertEquals(prior.size() + 1, after.size());
        prior.add(event1);
        assertEquals(prior, after);
    }
    
    @Test
    public void testRegisterMultipleUsers() {
        dao.registerEventUser(eventId1, user1.getUsername());
        dao.registerEventUser(eventId1, user2.getUsername());
        Event actual = dao.getEvent(eventId1);
        assertTrue(actual.getAttendees().contains(user1));
        assertTrue(actual.getAttendees().contains(user2));
    }
    
    @Test
    public void testRegisterForMultipleEvents() {
        List<Event> prior = dao.getAllEvents(user1.getUsername());
        if (prior.contains(event1)) {
            dao.removeEventUser(eventId1, user1.getUsername());
        }
        if (prior.contains(event2)) {
            dao.removeEventUser(eventId2, user1.getUsername());
        }
        
        dao.registerEventUser(eventId1, user1.getUsername());
        dao.registerEventUser(eventId2, user1.getUsername());
        List<Event> actual = dao.getEventsAttended(user1.getUsername());
        assertTrue(actual.contains(event1));
        assertTrue(actual.contains(event2));
    }
    
    
    @Test
    public void testRemoveUser() {
        dao.registerEventUser(eventId1, user1.getUsername());
        List<Event> prior = dao.getEventsAttended(user1.getUsername());
        dao.removeEventUser(eventId1, user1.getUsername());
        List<Event> after = dao.getEventsAttended(user1.getUsername());
        assertEquals(prior.size() - 1, after.size());
        prior.remove(event1);
        assertEquals(prior, after);
    }
    
    @Test
    public void testGetAllEvents() {
      List<Event> expected = Arrays.asList(event1, event2);
      assertEquals(expected, dao.getAllEvents());
        
    }
    
    @Test 
    public void testGetAllEventsEmpty() {
        teardown();
        assertTrue(dao.getAllEvents().isEmpty());
        
    }
    
    @Test
    public void testGetCreatedEvents() {
        event1.setCreator(user1.getUsername());
        dao.updateEvent(event1);
        
        event3.setCreator(user1.getUsername());
        eventId3 = dao.saveEvent(event3);
        
        assertTrue(dao.getAllEvents(user1.getUsername()).contains(event1));
        assertTrue(dao.getAllEvents(user1.getUsername()).contains(event3));
        assertFalse(dao.getAllEvents(user1.getUsername()).contains(event2));
        assertTrue(dao.getAllEvents(user2.getUsername()).isEmpty());
    }
    @Test
    public void testGetAttendedEvents() {
        dao.registerEventUser(eventId1, user1.getUsername());
        dao.removeEventUser(eventId2, user2.getUsername());
        assertTrue(dao.getEventsAttended(user1.getUsername()).contains(event1));
        assertFalse(dao.getEventsAttended(user1.getUsername()).contains(event2));
    }
    
    @Test
    public void testRemoveAttendedEvent() {
        dao.registerEventUser(eventId1, user1.getUsername());
        dao.removeEvent(eventId1);
        assertTrue(dao.getEventsAttended(user1.getUsername()).isEmpty());
    }
    
}
