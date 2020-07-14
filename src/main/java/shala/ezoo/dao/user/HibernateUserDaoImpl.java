package shala.ezoo.dao.user;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shala.ezoo.model.Role;
import shala.ezoo.model.User;
import shala.ezoo.model.UserRole;

@Repository
@Transactional
public class HibernateUserDaoImpl implements UserDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    public User getUser(String username) {
        User user = this.sessionFactory.getCurrentSession().get(User.class, username);
        return user;
    }

    
    public boolean saveUser(User user) {
        Session session = sessionFactory.openSession();
        
        try {
            session.beginTransaction();
            if (session.get(User.class, user.getUsername()) == null) {
                session.save(user);
                session.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            // TODO Throw Exception to propagate error rather than just returning false. 
        } finally {
            session.close();
        }
        
        return false;
        
    }

    
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    
    public User removeUser(String username) {
        User user = new User();
        user.setUsername(username);
        
        Session session = sessionFactory.openSession();
        
        try {
            session.beginTransaction();
            user = session.get(User.class, username);
            if (user != null) session.delete(user);
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        
        return null;
    }


    public boolean existsEmail(String email) {
        String hql = "select count(*) from User u where u.email=:email";
        return !sessionFactory.getCurrentSession().createQuery(hql)
                                                  .setParameter("email", email)
                                                  .uniqueResult().equals(0L);
    }
    
    public boolean existsUsername(String username) {
        String hql = "select count(*) from User u where u.username=:username";
        return !sessionFactory.getCurrentSession().createQuery(hql)
                                                  .setParameter("username", username)
                                                  .uniqueResult().equals(0L);
    }


    public void assignRole(String username, UserRole userRole) throws IllegalArgumentException {
        Session session = sessionFactory.openSession();
        
        try {
            session.beginTransaction();
            User user = session.get(User.class, username);
            if (user == null) {
                throw new IllegalArgumentException("Error Assigning Role. Username not found");
            }
            user.addRole(new Role(UserRole.toString(userRole)));
            session.update(user);
            
            
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        
    }

}
