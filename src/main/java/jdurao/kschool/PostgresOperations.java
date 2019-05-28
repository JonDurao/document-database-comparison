package jdurao.kschool;

import jdurao.kschool.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PostgresOperations {
    Session session;

    public void init() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("FROM areas WHERE id=1");
        Object o = query.getSingleResult();

        System.out.println(o.toString());
    }
}
