package shala.ezoo.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shala.ezoo.model.Event;
import shala.ezoo.model.User;

@Transactional
@Repository
public class HibernateEventDaoImpl implements EventDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Event getEvent(long eventId) {
        return this.sessionFactory.getCurrentSession().get(Event.class, eventId);
    }

    public long saveEvent(Event event) {
        Session session = sessionFactory.openSession();
        long generatedId = 0L;
        
        try {
            session.beginTransaction();
            if (session.get(Event.class, event.getEventId()) == null) {
                generatedId = (long) session.save(event);
                session.getTransaction().commit();
                return generatedId;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            // TODO Throw Exception to propagate error rather than just returning false. 
        } finally {
            session.close();
        }
        
        return generatedId;
        
    }

    public Event removeEvent(long eventId) {
        Event event = new Event();
        event.setEventId(eventId);
        
        Session session = sessionFactory.openSession();
        
        try {
            session.beginTransaction();
            event = session.get(Event.class, eventId);
            if (event != null) {
                event.setAttendees(null);
                session.update(event);
                
                session.delete(event);
                session.getTransaction().commit();
                return event;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        
        return null;
       
    }

    public void updateEvent(Event event) {
       sessionFactory.getCurrentSession().saveOrUpdate(event);
    }

    public boolean registerEventUser(long eventId, String username) {
        Session session = sessionFactory.openSession();
        
        try {
            session.beginTransaction();
            Event event = session.get(Event.class, eventId);
            User attendee = session.get(User.class, username);
            if (event == null) {
                throw new IllegalArgumentException("Error registered user for event. Event not found");
            }
            
            event.addAttendee(attendee);
            session.update(event);
            
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        
        return false;
    }

    public boolean removeEventUser(long eventId, String username) {
        Session session = sessionFactory.openSession();
        
        try {
            session.beginTransaction();
            Event event = session.get(Event.class, eventId);
            User attendee = session.get(User.class, username);
            if (event == null) {
                throw new IllegalArgumentException("Error un-registering user for event. Event not found");
            }
            
            event.removeAttendee(attendee);
            session.update(event);
            
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        
        return false; 
       
    }

    public List<Event> getAllEvents(String username) {
        String hql = "select u.events from User u where u.username=:username";
        Query query = sessionFactory.getCurrentSession().createQuery(hql, Collection.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    public List<Event> getAllEvents() {
        return this.sessionFactory.getCurrentSession().createQuery("from Event", Event.class).getResultList();
    }
    
}
