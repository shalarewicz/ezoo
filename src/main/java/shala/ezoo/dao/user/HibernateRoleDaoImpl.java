package shala.ezoo.dao.user;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shala.ezoo.model.Role;

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
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        
        return false;
    }
    
    public void removeRole(Role role) {
        Session session = sessionFactory.openSession();
        
        try {
            session.beginTransaction();
            session.remove(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

}
