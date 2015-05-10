package assign.etl;

import assign.domain.Meetings;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.logging.Logger;

/**
 * assignment6 Created by Majisto on 4/22/2015.
 */
public class DBLoader
{

    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory(boolean update) {
        Configuration configuration = new Configuration();
        if (update)
            configuration.configure("/hibernatelocalupdate.cfg.xml");
        else configuration.configure("/hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    Logger logger;

    public DBLoader(boolean update) {
        if (update) createSessionFactory(true);
        else createSessionFactory(false);
        logger = Logger.getLogger("EavesdropReader");
    }

    public Long addMeetings (List<Meetings> meetings){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Long assignmentId = null;
        try {
            tx = session.beginTransaction();
            for (Meetings m: meetings){
                session.save(m);
            }
//            meetings.forEach(session::merge);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
        finally {
            session.close();
        }
        return assignmentId;
    }

    public List<Meetings> getMeetings(String name, String year) throws Exception {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Criteria criteria = session.createCriteria(Meetings.class);

        List<Meetings> assignments = criteria.list();

        if (assignments.size() > 0) {
            return assignments;
        } else {
            return null;
        }
    }
}
