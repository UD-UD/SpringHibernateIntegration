package application.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ud on 19/3/17.
 *
 * serves the base platform to communicate with database
 */
@Transactional
public abstract class AbstractDao {
    @Autowired
    private SessionFactory sessionFactory;  //Session factory created in hibernateconfig will be injected here

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void persist(Object entity) {
        getSession().persist(entity);
    }

    public void delete(Object entity) {
        getSession().delete(entity);
    }
}
