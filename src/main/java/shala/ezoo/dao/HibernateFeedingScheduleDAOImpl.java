package shala.ezoo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;


@Repository
@Transactional
public class HibernateFeedingScheduleDAOImpl implements FeedingScheduleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public FeedingSchedule getScheduleByID(long id) {
		FeedingSchedule schedule = sessionFactory.getCurrentSession().get(FeedingSchedule.class, id);
		return schedule;
	}

	@Override
	public boolean saveSchedule(FeedingSchedule schedule) throws DataIntegrityViolationException{
	    Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            if (session.get(FeedingSchedule.class, schedule.getScheduleId()) == null) {
                session.save(schedule);
                session.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
	}

	@Override
    public List<FeedingSchedule> getAllSchedules() {
	   return sessionFactory.getCurrentSession()
	            .createQuery("from FeedingSchedule", FeedingSchedule.class).getResultList();
    }

	@Override
    public void updateSchedule(FeedingSchedule schedule) {
	    this.sessionFactory.getCurrentSession().update(schedule);
    }

	@Override
    public FeedingSchedule removeSchedule(long scheduleId) {
	    Session session = this.sessionFactory.openSession();
	    try {
	        session.beginTransaction();
	        FeedingSchedule fs = session.get(FeedingSchedule.class, scheduleId);
	        if (fs != null) {
	            for (Animal a : fs.getAnimals()) {
	                a.setFeedingSchedule(null);
	                session.update(a);
	            }
	            session.delete(fs);
	            session.getTransaction().commit();
	            return fs;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    } finally {
	        session.close();
	    }
	    
	    return null;
        
    }


	@Override
    public boolean setFeedingSchedule(FeedingSchedule schedule, Animal animal) {
	    if (animal != null && schedule !=null) {
    	    FeedingSchedule prev = animal.getFeedingSchedule();
    	    
    	    Session session = this.sessionFactory.openSession();
    	    try {
    	        animal.setFeedingSchedule(schedule);
    	        schedule.addAnimal(animal);
    	        session.beginTransaction();
    	        session.saveOrUpdate(schedule);
    	        session.saveOrUpdate(animal);
    	        session.getTransaction().commit();	
    	        return true;
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        session.getTransaction().rollback();
    	        animal.setFeedingSchedule(prev);
    	        schedule.removeAnimal(animal);
    	    } finally {
    	        session.close();
    	    }
	    }
	    return false;
    }

	@Override
    public boolean setFeedingSchedule(long schedule, long animal) {
	   Session session = this.sessionFactory.openSession();
	   try {
    	   FeedingSchedule fs = session.get(FeedingSchedule.class, schedule);
    	   Animal a = session.get(Animal.class, animal);
    	   session.close();
    	   if (a != null && fs !=null) {
    	       return this.setFeedingSchedule(fs, a);
    	   }
	   } catch (Exception e) {
            e.printStackTrace();
	   }
	   return false;
    }

}
