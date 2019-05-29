package jdurao.kschool;

import jdurao.kschool.entities.Areas;
import jdurao.kschool.pojo.AreaJson;
import jdurao.kschool.util.HibernateUtil;
import jdurao.kschool.util.TestDataGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx16G"})
@Warmup(iterations = 0)
@Measurement(iterations = 4)
public class PostgresOperations {
    List<String> valuesSmall = new ArrayList<>();
    List<String> valuesMedium = new ArrayList<>();
    List<String> valuesLarge = new ArrayList<>();
    String inputSmall;
    String inputMedium;
    String inputLarge;

    @Param({"1"})
    public int iterations;

    public void main() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PostgresOperations.class.getSimpleName())
                .forks(1)
                .operationsPerInvocation(1)
                .build();

        new Runner(opt).run();
    }

    @Setup(Level.Invocation)
    public void aCreationData() {
        for (int i = 0; i < 100; i++) {
            valuesSmall.add("('" + TestDataGenerator.createAreaJson((long) i) + "')");
        }
        inputSmall = String.join(",", valuesSmall);
        for (int i = 0; i < 1000; i++) {
            valuesMedium.add("('" + TestDataGenerator.createLabelJson((long) i) + "')");
        }
        inputMedium = String.join(",", valuesMedium);
        for (int i = 0; i < 10000; i++) {
            valuesLarge.add("('" + TestDataGenerator.createArtistJson((long) i) + "')");
        }
        inputLarge = String.join(",", valuesLarge);
    }

    @Benchmark
    public void bInsertOneRecord() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.createNativeQuery("INSERT INTO areas (area) VALUES ('" + TestDataGenerator.createAreaJson(UUID.randomUUID().getMostSignificantBits()) + "')").executeUpdate();
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Benchmark
    public void cInsertMultipleRecordsSmall() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO areas (area) VALUES " + inputSmall).executeUpdate();

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Benchmark
    public void dInsertMultipleRecordsMedium() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO labels (label) VALUES " + inputMedium).executeUpdate();

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Benchmark
    public void eInsertMultipleRecordsLarge() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO artists (artist) VALUES " + inputLarge).executeUpdate();

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Benchmark
    public void fDeleteFieldOne() {
        for (int i = 0; i < iterations; i++){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();

            List<Areas> e = (List<Areas>) entityManager.createNativeQuery("SELECT * FROM areas a WHERE a.area->'id' = '999'", Areas.class).getResultList();

            System.out.println("Hola " + e.get(0).getId());

            entityTransaction.commit();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
