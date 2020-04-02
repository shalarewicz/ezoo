package shala.ezoo.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import shala.ezoo.model.Animal;
import shala.ezoo.model.FeedingSchedule;

@Repository
@Transactional
public class HibernateAnimalDAOImpl implements AnimalDAO {
	
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
	public void saveAnimal(Animal animal) {
		sessionFactory.getCurrentSession().save(animal);
	}

	@Override
	public void updateAnimal(Animal animal) {
		// Overwrites the existing record in the database matching the primary key animalId
		sessionFactory.getCurrentSession().update(animal);
	}
	
	@Override
	public void removeAnimal(long animalId) {
		Animal a = new Animal();
		a.setAnimalID(animalId);
		sessionFactory.getCurrentSession().delete(a);
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
    public void removeSchedule(long animalId) {
	    Animal a = getAnimalByID(animalId);
	    a.setFeedingSchedule(null);
	    updateAnimal(a);
        
    }
	

}
