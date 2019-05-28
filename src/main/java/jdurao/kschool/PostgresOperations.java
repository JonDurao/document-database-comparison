package jdurao.kschool;

import jdurao.kschool.entities.Areas;
import jdurao.kschool.pojo.AreaJson;
import jdurao.kschool.util.HibernateUtil;
import jdurao.kschool.util.TestDataGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;


public class PostgresOperations {
    static Session session;
    static Transaction tx;

    public static void init() {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        insertOneDocumentSmallDatabase();
    }

    @Benchmark
    public static void insertOneDocumentSmallDatabase() {

        HibernateUtil.shutdown();
    }
}
