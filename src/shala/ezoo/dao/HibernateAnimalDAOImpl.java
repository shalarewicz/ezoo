package shala.ezoo.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;


import shala.ezoo.model.Animal;

@Repository
@Transactional(rollbackOn=DataIntegrityViolationException.class)
public class HibernateAnimalDAOImpl implements AnimalDAO {
	
    @Autowired
	private SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Animal getAnimalByID(long id) {
		Animal animal = sessionFactory.getCurrentSession().get(Animal.class, id);
		return animal;
	}

	@Override
	public boolean saveAnimal(Animal animal) throws DataIntegrityViolationException {
	    Session session = sessionFactory.openSession();
	    try {
	        session.beginTransaction();
    	    if (session.get(Animal.class, animal.getAnimalID()) == null) {
    	        session.save(animal);
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
	public void updateAnimal(Animal animal) {
		// Overwrites the existing record in the database matching the primary key animalId
		sessionFactory.getCurrentSession().saveOrUpdate(animal);
	}
	
	@Override
	public Animal removeAnimal(long animalId) {
		Animal a = new Animal();
		a.setAnimalID(animalId);
		Session session = sessionFactory.openSession();
		try {
		    session.beginTransaction();
    		a = session.get(Animal.class, animalId);
    		if (a!= null) session.delete(a);
            session.getTransaction().commit();
		} catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return a;
	}

	@Override
	public List<Animal> getAllAnimals() {
        return sessionFactory.getCurrentSession().createQuery("from Animal", Animal.class).getResultList();
	}

	@Override
	public List<Animal> getAllAnimals(long scheduleId) {
		String hql = "from Animal where feedingschedule = :scheduleId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql, Animal.class);
        query.setParameter("scheduleId", scheduleId);
        List<Animal> result = query.getResultList();
        return result;
	}

	@Override
    public boolean removeSchedule(long animalId) {
	    Animal a = getAnimalByID(animalId);
	    a.setFeedingSchedule(null);
	    return saveAnimal(a);
        
    }
	

}
