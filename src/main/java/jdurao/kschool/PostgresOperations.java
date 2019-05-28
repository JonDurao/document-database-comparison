package jdurao.kschool;

import jdurao.kschool.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class PostgresOperations {
    Session session;

    public void init() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
}
