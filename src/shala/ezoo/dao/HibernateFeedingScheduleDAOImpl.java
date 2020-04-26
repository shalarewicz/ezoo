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

	/**
	 * Adds the given schedule to the database
	 * @param schedule 
	 * @throws DataIntegrityViolationException if a constraint violation occurs. 
	 */
	public void saveSchedule(FeedingSchedule schedule) throws DataIntegrityViolationException{
        sessionFactory.getCurrentSession().save(schedule);
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
    public void removeSchedule(long scheduleId) {
	    FeedingSchedule fs = new FeedingSchedule();
	    fs.setScheduleId(scheduleId);
	    this.sessionFactory.getCurrentSession().remove(fs);
        
    }


	@Override
    public void setFeedingSchedule(FeedingSchedule schedule, Animal animal) {
	    if (animal != null && schedule !=null) {
	        System.out.println(animal);
	        System.out.println(schedule);
    	    FeedingSchedule prev = animal.getFeedingSchedule();
    	    
    	    Session session = this.sessionFactory.openSession();
    	    try {
    	        animal.setFeedingSchedule(schedule);
    	        schedule.addAnimal(animal);
    	        session.beginTransaction();
    	        session.saveOrUpdate(schedule);
    	        session.saveOrUpdate(animal);
    	        session.getTransaction().commit();	
    	        session.close();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        session.getTransaction().rollback();
    	        animal.setFeedingSchedule(prev);
    	        schedule.removeAnimal(animal);
    	    }
	    }
    }

	@Override
    public void setFeedingSchedule(long schedule, long animal) {
	   Session session = this.sessionFactory.openSession();
	   FeedingSchedule fs = session.get(FeedingSchedule.class, schedule);
	   Animal a = session.get(Animal.class, animal);
	   session.close();
	   if (a != null && fs !=null) {
	       this.setFeedingSchedule(fs, a);
	   }
    }

}
