package shala.ezoo.dao.user;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shala.ezoo.model.Role;
import shala.ezoo.model.UserRole;

@Repository
@Transactional
public class HibernateRoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public Role getRole(String name) {
        String hql = "from Role r where r.name=:name";
        return (Role) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", name).uniqueResult();
    }

    public boolean saveRole(Role role) {
        Session session = sessionFactory.openSession();
        
        try {
            session.beginTransaction();
            session.save(role);
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
    
    public void removeRole(UserRole role) {
        Session session = sessionFactory.openSession();
        
        try {
            session.beginTransaction();
            Query query = sessionFactory.getCurrentSession().createQuery("delete from Role where name=:role");
            query.setParameter("role", UserRole.toString(role));
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public List<Role> getAllRoles() {
        return sessionFactory.getCurrentSession().createQuery("from Role", Role.class).getResultList();
    }

    public List<Role> getAllRoles(String username) {
        String hql = "select u.roles from User u where u.username=:username";
        Query query = sessionFactory.getCurrentSession().createQuery(hql, Collection.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

}
